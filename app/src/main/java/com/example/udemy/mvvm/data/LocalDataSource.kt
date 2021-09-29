package com.example.udemy.mvvm.data

import com.example.udemy.mvvm.data.database.RecipesDao
import com.example.udemy.mvvm.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//di 는 반환값에 맞는 모듈을 자동으로 끌어쓴다.

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {

    fun readDatabase() : Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDao.insertRecipes(recipesEntity)
    }

}