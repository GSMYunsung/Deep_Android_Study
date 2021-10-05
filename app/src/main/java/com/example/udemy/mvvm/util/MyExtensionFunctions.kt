package com.example.udemy.mvvm.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

//라이브 데이터 객체를 매번 관찰하지 않고 한 번만 관찰한다.

fun <T> LiveData<T>.observeOnce(lifecycleOwner : LifecycleOwner, observer : Observer<T>){
    observe(lifecycleOwner, object : Observer<T>{
        override fun onChanged(t: T) {
            removeObserver(this)
            observer.onChanged(t)
        }
    })
}