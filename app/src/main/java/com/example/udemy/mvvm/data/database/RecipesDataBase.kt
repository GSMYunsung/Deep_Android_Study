package com.example.udemy.mvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

// 데이터베이스 선언부

// exportSchema 는 스키마를 외부 텍스트로 내보내는 명령어!
@Database(
    entities = [RecipesEntity::class],
    version = 1,
    exportSchema = false
)

// abstract 를 사용함으로써 기능을 구현하지않고 임시로 함수를 작성한다.
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDataBase : RoomDatabase() {

    abstract fun recipesDao() : RecipesDao
}