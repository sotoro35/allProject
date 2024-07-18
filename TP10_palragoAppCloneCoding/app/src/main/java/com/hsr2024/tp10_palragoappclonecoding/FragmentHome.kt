package com.hsr2024.tp10_palragoappclonecoding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.hsr2024.tp10_palragoappclonecoding.databinding.HomeFragmentBinding

class FragmentHome :Fragment(){

    lateinit var binding:HomeFragmentBinding
    var pagerList = mutableListOf<ItemPager>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= HomeFragmentBinding.inflate(inflater,container,false)


        pagerList.add(ItemPager(R.drawable.event01))
        pagerList.add(ItemPager(R.drawable.event02))
        pagerList.add(ItemPager(R.drawable.event03))
        pagerList.add(ItemPager(R.drawable.event04))



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.pager.adapter= FragmentHomeAdapterPager(requireContext(),pagerList)

        binding.pagerNum2.text= " / ${pagerList.size} +"
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                var index= position
                binding.pagerNum1.text= " ${index+1} "

            }

        })



    }

}