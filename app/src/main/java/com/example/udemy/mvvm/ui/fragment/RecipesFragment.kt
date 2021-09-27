package com.example.udemy.mvvm.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.udemy.R
import com.example.udemy.databinding.FragmentRecipesBinding
import com.example.udemy.mvvm.MainViewModel
import com.example.udemy.mvvm.adapter.RecipesAdapter
import com.example.udemy.mvvm.util.Constants.Companion.API_KEY
import com.example.udemy.mvvm.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.example.udemy.mvvm.util.Constants.Companion.QUERY_API_KEY
import com.example.udemy.mvvm.util.Constants.Companion.QUERY_DIET
import com.example.udemy.mvvm.util.Constants.Companion.QUERY_FULL_INGREDIENTS
import com.example.udemy.mvvm.util.Constants.Companion.QUERY_NUMBER
import com.example.udemy.mvvm.util.Constants.Companion.QUERY_TYPE
import com.example.udemy.mvvm.util.NetWorkResult
import dagger.hilt.android.AndroidEntryPoint

// 이곳의 Fragment 의 mainViewModel 객체 가 ViewModel 종속성을 주입 받았기 때문에 다음 주석을 달아야한다.
@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel : MainViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var binding : FragmentRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipes, container, false)

        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        setupRecyclerView()
        requestApiData()

        return binding.root
    }

    private fun requestApiData(){
        mainViewModel.getRecipes(applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, {response ->
            when(response){
                is NetWorkResult.Success ->{
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetWorkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(requireContext(),response.message.toString(),Toast.LENGTH_SHORT)
                }
                is NetWorkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun applyQueries() : HashMap<String,String> {
        val queries : HashMap<String,String> = HashMap()

        //우리가 얻고자하는 레시피의 양
        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FULL_INGREDIENTS] = "true"

        return queries
    }

    private fun setupRecyclerView(){
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect(){
        binding.recyclerview.showShimmer()
    }

    private fun hideShimmerEffect(){
        binding.recyclerview.hideShimmer()
    }

}