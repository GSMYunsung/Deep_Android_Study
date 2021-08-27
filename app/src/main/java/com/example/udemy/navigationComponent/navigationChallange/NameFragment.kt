package com.example.udemy.navigationComponent.navigationChallange

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.udemy.R
import com.example.udemy.databinding.FragmentHome2Binding
import com.example.udemy.databinding.FragmentNameBinding

class NameFragment : Fragment() {
    private lateinit var binding : FragmentNameBinding
    var name = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_name, container, false)

        binding.nameNextButton.setOnClickListener {
            val bundle = bundleOf("user_name" to binding.nameEdit.text.toString())

            it.findNavController().navigate(R.id.action_nameFragment_to_EmailFragment,bundle) }

        return binding.root
    }
}