package com.example.udemy.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.udemy.databinding.FragmentRecipesBinding
import com.example.udemy.databinding.RecipesRowLayoutBinding
import com.example.udemy.mvvm.models.FoodRecipe
import com.example.udemy.mvvm.models.Result
import com.example.udemy.mvvm.util.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipe = emptyList<Result>()

    //UI구성요소를 binding 형태로 넘겨준다.
    class MyViewHolder(private val binding: RecipesRowLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(result: Result){
                binding.result = result
                //변경사항이 있을 때 마다 화면을 업데이트한다. (UI 자체를 건드린다.)
                binding.executePendingBindings()
            }

        //대충 자바와 비교하자면 전역변수
        companion object{
            //화면의 기본 구성? 을 여기에서 미리 만들어준다. (layout 기본 구성요소)
            fun from(perent : ViewGroup) : MyViewHolder{
                val layoutInflater = LayoutInflater.from(perent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater,perent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //기본 화면 구성
       return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = recipe[position]
        //화면 요소 구성
        holder.bind(currentResult)
    }

    override fun getItemCount(): Int {
        return recipe.size
    }

    fun setData(newData : FoodRecipe){

        //변경사항이 있을 때 마다 화면을 업데이트한다. 하지만 계속해서 모든 UI 값을 불러오면 안드로이드의 메모리를 많이 잡아먹는다.
        //notifyDataSetChanged()

        //따라서 레시피의 업데이트 된 부분만을 업데이트하기위해 아래와같은 방법을 쓴다.

        //두 레시피의 다른점 찾기
        val recipesDifful = RecipesDiffUtil(recipe,newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDifful)
        recipe = newData.results
        //지금까지의 변경사항 전달
        diffUtilResult.dispatchUpdatesTo(this)
    }
}