package com.example.udemy.mvvm.models


import com.google.gson.annotations.SerializedName

//음식의 종합적 레시피
data class FoodRecipe(
    @SerializedName("results")
    val results: Array<Result>,
)