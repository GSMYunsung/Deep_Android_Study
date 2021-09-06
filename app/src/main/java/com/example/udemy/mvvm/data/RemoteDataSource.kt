package com.example.udemy.mvvm.data

import com.example.udemy.mvvm.data.network.FoodRecipesApi
import com.example.udemy.mvvm.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

//데이터 소스 2가지

//1. Food, Recipes API 또는 다음에서 데이터를 가져올 원격 데이터 소스
//2. 로컬 데이터베이스를 처리할 로컬 데이터 소스

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
){
    suspend fun getRecipes(queries : Map<String,String>) : Response<FoodRecipe>{
        return foodRecipesApi.getRecipes(queries)
    }
}