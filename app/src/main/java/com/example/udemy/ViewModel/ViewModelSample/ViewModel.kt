package com.example.udemy.ViewModel.ViewModelSample

import androidx.lifecycle.ViewModel

class ViewModel : ViewModel(){
    private var count = 0

    fun getCurrentCount() : Int{
        return count
    }

    fun getUpdateCount(): Int{
        return ++count
    }

}