package com.example.udemy.ViewModel.ViewModelChallange

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelChallangeViewModel : ViewModel() {

    private val _num = MutableLiveData<Int>()
    val num: MutableLiveData<Int>
        get() = _num

    fun numAdd() : Int?{
        return _num.value?.plus(1)
    }

    fun numSubtract() : Int?{
        return _num.value?.plus(1)
    }

}