package com.hsr2024.test60

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.hsr2024.test60.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            //binding.tv.text= "안녕하세요"
            val fragmentManager:FragmentManager= supportFragmentManager
            val myFragment:MyFragment = fragmentManager.findFragmentById(R.id.frag) as MyFragment
            myFragment.binding.tv.text= "나는 메인버튼으로 프래그먼트를 제어한다"


        }
        binding.btn2.setOnClickListener {
            binding.tv2.text= binding.inputLayout.editText!!.text.toString()
            binding.inputLayout.editText!!.setText("")
        }

    }

    var itemList: MutableList<Item> = mutableListOf()

    override fun onResume() {
        super.onResume()

        itemList.add(Item(R.drawable.newyork,"뉴욕"))
        itemList.add(Item(R.drawable.sydney,"시드니"))
        itemList.add(Item(R.drawable.paris,"파리"))
        itemList.add(Item(R.drawable.newyork,"뉴욕1"))
        itemList.add(Item(R.drawable.sydney,"시드니2"))
        itemList.add(Item(R.drawable.paris,"파리3"))

        binding.recyclerView.adapter= MyAdapter(this,itemList)
    }
}

// 잊지말고 data 쓰기... 주소 비교가 아니니까!!
data class Item constructor(var imgId:Int, var title:String)