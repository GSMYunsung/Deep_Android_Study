package com.example.udemy.mvvm

import androidx.room.TypeConverter
import com.example.udemy.mvvm.models.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class RecipesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe) : String{
        // Json 형태의 데이터를 room 에 저장하기위해 String 형태로 바꾸어 준다.
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data : String) : FoodRecipe {

        //기존의 반환하였던 string 값을 Json형태로 바꾸어 준다.

        val lisType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data,lisType)
    }

}