package com.example.udemy.viewModel.viewModelSample

import androidx.lifecycle.ViewModel

class viewModel : ViewModel(){
    private var count = 0

    fun getCurrentCount() : Int{
        return count
    }

    fun getUpdateCount(): Int{
        return ++count
    }

}