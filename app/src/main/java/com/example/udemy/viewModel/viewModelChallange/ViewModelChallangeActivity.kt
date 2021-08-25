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

        //라이브 데이터 + 데이터 바인딩 결합 챌린지 성공

        val viewmodel = ViewModelProvider(this).get(ViewModelChallangeViewModel::class.java)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_model_challange)

        //이 뷰에 라이프사이클 오너를 적용해 변경사항이 저장되게 만든다.
        binding.apply {
            lifecycleOwner = this@ViewModelChallangeActivity
            binding.clickEvent = viewmodel
        }

    }
}