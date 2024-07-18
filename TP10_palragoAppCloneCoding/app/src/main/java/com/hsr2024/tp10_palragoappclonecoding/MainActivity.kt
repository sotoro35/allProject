package com.hsr2024.tp10_palragoappclonecoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.OneShotPreDrawListener.add
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayoutMediator
import com.hsr2024.tp10_palragoappclonecoding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var fragment:Array<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fragment= arrayOf(
            FragmentHome(),
            FragmentStore()
        )

        val fragmentManager= supportFragmentManager.beginTransaction().apply {
            add(R.id.container,fragment[0],"Home")
            commit()
        }

        binding.bnv.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container,fragment[0]).commit()
                    true
                }

                R.id.store ->{
                    supportFragmentManager.beginTransaction().replace(R.id.container,fragment[1]).commit()
                    true
                }

                R.id.talk -> {
                    true
                }

                R.id.coupon -> {
                    true

                }

                else -> false
            }

        }



    }

}