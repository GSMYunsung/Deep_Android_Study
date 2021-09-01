package com.example.udemy.mvvm.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.udemy.R
import com.example.udemy.databinding.ActivityFoodMainBinding

class FoodMainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding : ActivityFoodMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_food_main)

        //네비게이션 컴포넌트 화면 정의
        navController = supportFragmentManager.findFragmentById(R.id.navHostFragment)!!.findNavController()

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

        //위의 엑션바에 해당 포지션 String 값 보내주기 ex) RecipesFragment = Recipes
        setupActionBarWithNavController(navController,appBarConfiguration)

    }

    override fun onSupportNavigateUp() : Boolean{
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}