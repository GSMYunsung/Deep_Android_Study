package com.example.udemy.mvvm.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.udemy.R

class RecipesRowBinding {

    companion object  {

        //바인딩 어뎁터로 해당 함수 지정
        @BindingAdapter("setNumberOfLikes")
        //컴파일러에게 함수를 정적으로 만들라고 명령
        @JvmStatic
        fun setNumberOfLikes(textView : TextView, likes : Int){
            textView.text = likes.toString()
        }

        @BindingAdapter("setNumberOfMinute")
        @JvmStatic
        fun setNumberOfMinute(textView : TextView, minute : Int){
            textView.text = minute.toString()
        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view : View, vegan : Boolean){
            if(vegan){
                when(view){
                    is TextView -> {
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    }
                    is ImageView -> {
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                 R.color.green
                            )
                        )
                    }
                }
            }
        }
    }
}