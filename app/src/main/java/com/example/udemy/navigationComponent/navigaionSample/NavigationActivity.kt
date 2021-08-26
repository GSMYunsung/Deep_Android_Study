package com.example.udemy.navigationComponent.navigaionSample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.udemy.R
import com.example.udemy.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_navigation)

    }
}