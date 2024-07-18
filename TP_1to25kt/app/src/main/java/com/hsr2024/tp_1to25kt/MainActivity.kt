package com.hsr2024.tp_1to25kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hsr2024.tp_1to25kt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStage1.setOnClickListener { startActivity(Intent(this, Stage1Activity::class.java))}
        binding.btnStage2.setOnClickListener { startActivity(Intent(this,Stage2Activity::class.java)) }
        binding.btnScore.visibility= View.GONE

    }


}