package com.example.udemy.navigationComponent.navigaionSample

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.udemy.R
import com.example.udemy.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.submitButton.setOnClickListener {

            //EditText text 유효성 검사
            if(!TextUtils.isEmpty(binding.navEdittext.text.toString()))
            {
                //NavigationComponent 에서는 대상간의 데이터전달이 권장되지 않는다.
                val bundle = bundleOf("user_name" to binding.navEdittext.text.toString())
                //다음 Fragment로 넘어가기위해선 View가 필요하다.
                //HomeFragment > SecondFragment
                it.findNavController().navigate(R.id.action_homeFragment_to_secondFragment,bundle)
            }
            else{
                Toast.makeText(activity,"값을 넣어주세요!",Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }
}