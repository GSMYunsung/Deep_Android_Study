package com.example.udemy.viewModel.viewModelChallange

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelChallangeViewModel : ViewModel() {

    private val _value = MutableLiveData<String>()
    val value: MutableLiveData<String>
        get() = _value

    private val _num = MutableLiveData<String>()
    val num: MutableLiveData<String>
        get() = _num

    init {
        _value.value = "0"
    }

    fun numAdd() {
         _value.value = (_value.value.toString().toInt().plus((_num.value.toString().toInt()))).toString()
    }

    fun numSubtract() {
        _value.value = (_value.value.toString().toInt().minus((_num.value.toString().toInt()))).toString()
    }

}