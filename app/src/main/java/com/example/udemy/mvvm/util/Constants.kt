package com.example.udemy.mvvm.util

class Constants {

    //우리가 불러올 Api 에서는 기본 킷값과 사용자의 시리얼 번호가 필요하기때문에!
    companion object{

        const val API_KEY = "f926b9f4bd674b5b819f9ea455a7b45b"
        const val BASE_URL = "https://api.spoonacular.com"

        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apikey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FULL_INGREDIENTS = "fillIngredients"

        //ROOM DataBase
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"
    }
}