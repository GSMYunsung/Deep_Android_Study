package com.example.udemy.viewModel.viewModelChallange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.udemy.R
import com.example.udemy.databinding.ActivityViewModelChallangeBinding

class ViewModelChallangeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityViewModelChallangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel = ViewModelProvider(this).get(ViewModelChallangeViewModel::class.java)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_model_challange)

        binding.apply {
            lifecycleOwner = this@ViewModelChallangeActivity
            binding.clickEvent = viewmodel
        }

    }
}