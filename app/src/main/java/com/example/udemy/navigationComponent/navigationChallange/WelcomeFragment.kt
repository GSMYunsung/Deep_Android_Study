package com.example.udemy.navigationComponent.navigationChallange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.udemy.R
import com.example.udemy.databinding.FragmentHome2Binding
import com.example.udemy.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding : FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)

        var username = arguments?.getString("user_name")
        var useremail = arguments?.getString("user_email")

        binding.viewTermsButton.setOnClickListener {

            val bundle = bundleOf(
            "user_email" to useremail,
            "user_name" to username
        )
            it.findNavController().navigate(R.id.action_welcomeFragment_to_termsFragment,bundle)
        }

        return binding.root
    }
}