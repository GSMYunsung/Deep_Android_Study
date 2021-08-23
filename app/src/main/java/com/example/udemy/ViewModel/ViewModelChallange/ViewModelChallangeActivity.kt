package com.example.udemy.ViewModel.ViewModelChallange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.udemy.R
import com.example.udemy.databinding.ActivityViewModelChallangeBinding

class ViewModelChallangeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewModelChallangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_model_challange)

    }
}