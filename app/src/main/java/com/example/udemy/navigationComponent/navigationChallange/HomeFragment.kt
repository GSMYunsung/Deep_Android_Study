package com.example.udemy.navigationComponent.navigationChallange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.udemy.R
import com.example.udemy.databinding.FragmentHome2Binding
import com.example.udemy.databinding.FragmentSecondBinding

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHome2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home2, container, false)

        binding.signupButton.setOnClickListener { it.findNavController().navigate(R.id.action_homeFragment2_to_nameFragment) }
        binding.termsButton.setOnClickListener { it.findNavController().navigate(R.id.action_homeFragment2_to_termsFragment) }

        return binding.root
    }
}