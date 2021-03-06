package com.example.udemy.mvvm.data.network

import com.example.udemy.mvvm.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesApi {

    //코루틴으로 사용하기 위해서 Response 객체로 보내준다.
    //백그라운드 스레드에서 실행!
    //최초참조
    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries : Map<String,String>
    ):Response<FoodRecipe>

}