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
import com.example.udemy.databinding.FragmentEmailBinding
import com.example.udemy.databinding.FragmentHome2Binding

class EmailFragment : Fragment() {
    private lateinit var binding : FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_email, container, false)

        var username = arguments?.getString("user_name")

        binding.submitButton.setOnClickListener {
            val bundle = bundleOf(
                "user_email" to binding.emailEdit.text.toString(),
                "user_name" to username
            )
            it.findNavController().navigate(R.id.action_emailFragment_to_welcomeFragment,bundle) }

        return binding.root
    }
}