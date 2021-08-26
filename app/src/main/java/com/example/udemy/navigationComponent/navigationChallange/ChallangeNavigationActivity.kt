package com.example.udemy.navigationComponent.navigationChallange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.udemy.R
import com.example.udemy.databinding.ActivityChallangeNavigationBinding
import com.example.udemy.databinding.ActivityNavigationBinding

class ChallangeNavigationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityChallangeNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_challange_navigation)
    }
}