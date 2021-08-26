package com.example.udemy.viewModel.viewModelChallange

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelChallangeViewModel : ViewModel() {

    //캡슐화를 지향하기위해 라이브데이터 변수 두개를 결합한다.
    //최종 값 변수
    private val _value = MutableLiveData<Int>()
    val value: LiveData<Int> get() = _value

    init {
        _value.value = 0
    }

    fun numAdd(num : String) {
         _value.value = _value.value?.plus(num.toInt())
    }

    fun numSubtract(num : String) {
        _value.value = _value.value?.minus(num.toInt())
    }

}