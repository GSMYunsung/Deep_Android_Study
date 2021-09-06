package com.example.udemy.mvvm

import com.example.udemy.mvvm.data.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

// 활동의 수명 동안 존재해야 하는 바인딩에 대한 범위 주석, 생존 구성.

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remote = remoteDataSource

}