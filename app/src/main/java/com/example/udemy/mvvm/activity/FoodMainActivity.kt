package com.example.udemy.mvvm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.udemy.R
import com.example.udemy.databinding.ActivityFoodMainBinding
import com.example.udemy.databinding.FragmentHomeBinding

class FoodMainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding : ActivityFoodMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_food_main)

        //네비게이션 컴포넌트 화면 정의
        navController = findNavController(R.id.navHostFragment)

        //네비게이션 컴포넌트 xml 구성요소 정의하기
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.recipesFragment
                ,R.id.favoriteRecipesFragment
                ,R.id.foodJokeFragment
            )
        )

        //bottomNavigationView 에 navController 를 정의해준다.
        binding.bottomNavigationView.setupWithNavController(navController)

        setupActionBarWithNavController(navController,appBarConfiguration)

    }

    override fun onSupportNavigateUp() : Boolean{
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}