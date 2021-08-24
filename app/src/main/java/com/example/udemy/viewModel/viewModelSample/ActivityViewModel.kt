package com.example.udemy.viewModel.viewModelSample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.udemy.R
import com.example.udemy.databinding.ActivityViewModelActivityBinding

class ActivityViewModel : AppCompatActivity() {
    private lateinit var binding : ActivityViewModelActivityBinding
    private lateinit var viewModel: viewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_model_activity)
        viewModel = ViewModelProvider(this).get(viewModel::class.java)
        binding.counterText.text = viewModel.getCurrentCount().toString()

        binding.button.setOnClickListener {
            viewModel.getUpdateCount()
            binding.counterText.text = viewModel.getCurrentCount().toString()
        }
    }
}