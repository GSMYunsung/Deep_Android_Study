package com.example.udemy.mvvm.di

import android.content.Context
import androidx.room.Room
import com.example.udemy.mvvm.data.database.RecipesDataBase
import com.example.udemy.mvvm.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//RoomDataBase 를 빌드하는 방법

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context : Context
    ) = Room.databaseBuilder(
        context,
        RecipesDataBase::class.java,
        DATABASE_NAME
    ).build()

    //추상클래스를 가져온다. 아마 recipesDao 를 di 를 이용해 가져오는 방법같다.

    @Singleton
    @Provides
    fun provideDao(database : RecipesDataBase) = database.recipesDao()
}