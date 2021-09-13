package com.example.udemy.mvvm.di

import com.example.udemy.mvvm.data.network.FoodRecipesApi
import com.example.udemy.mvvm.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Suppress("DEPRECATION")
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //종속성 끼리 자신의 반환값에 맞는 함수를 자동으로 연결

    //OkHttpClient 빌더

    @Singleton
    @Provides
    fun provideHttpClient() : OkHttpClient{
        return OkHttpClient.Builder()
            .readTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .build()
    }


    //Gson 빌더
    //GsonConverterFactory 는 Retrofit 이 Gson 형태로 반환한다.

    @Singleton
    @Provides
    fun provideConverterFactory() : GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    //레트로핏 인스턴스를 생성하기위한 종속성
    //기본적인 레트로핏 생성

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    //레트로핏 라이브러리 사용
    //외부 라이브러리 사용은 Provides 옵션을 추가시켜주어야 한다.
    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : FoodRecipesApi {
        return retrofit.create(FoodRecipesApi::class.java)
    }
}