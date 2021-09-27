package com.example.udemy.mvvm

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

// exportSchema 는 스키마를 외부 텍스트로 내보내는 명령어!
@Database(
    entities = [RecipesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters()
class RecipesDataBase : RoomDatabase() {

    abstract fun recipesDao() : RecipesDao
}