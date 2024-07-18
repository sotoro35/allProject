package com.hsr2024.ex60viewbinding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hsr2024.ex60viewbinding.databinding.FragmentMyBinding

class MyFragment : Fragment() {

    // 뷰들을 대신 참조해주는 바인딩 객체 참조변수
    lateinit var binding: FragmentMyBinding

    // 프래그먼트가 보여줄 뷰객체를 만들어서 리턴해주는 콜백 메소드 - 라이프사이클


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //원래는 프래그먼트가 직접 뷰객체를 생성했었음. xml의 뷰들을 대신 참조해주는 바인딩 객체를 이용

        binding= FragmentMyBinding.inflate(inflater,container,false)
        return binding.root
    }

    //뷰가 만들어지고 난 후...
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btn.setOnClickListener { binding.tv.text= "zzzzzzz" }
    }


}