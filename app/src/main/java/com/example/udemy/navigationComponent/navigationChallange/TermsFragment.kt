package com.example.udemy.navigationComponent.navigationChallange

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.udemy.R
import com.example.udemy.databinding.FragmentTermsBinding
import com.example.udemy.databinding.FragmentWelcomeBinding

class TermsFragment : Fragment() {
    private lateinit var binding : FragmentTermsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_terms, container, false)

        var username = arguments?.getString("user_name")
        var useremail = arguments?.getString("user_email")

        binding.emailText.text = useremail
        binding.nameText.text = username
        binding.welcomeText.text = "Welcome!"

        return binding.root
    }
}