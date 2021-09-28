package com.example.udemy.mvvm.data

import com.example.udemy.mvvm.data.database.RecipesDao
import com.example.udemy.mvvm.data.database.RecipesEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {



    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDao.insertRecipes(recipesEntity)
    }

}