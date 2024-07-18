package com.hsr2024.test60

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hsr2024.test60.databinding.FragmentMyBinding

class MyFragment:Fragment() {

    lateinit var binding: FragmentMyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener { binding.tv.text = "방가방가~" }
        binding.btn2.setOnClickListener {
            val mainActivity:MainActivity = activity as MainActivity
            mainActivity.binding.tv.text = " 프래그먼트 버튼으로 메인 조종하기"
        }


    }
}