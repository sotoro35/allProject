package com.hsr2024.tp_1to25kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hsr2024.tp_1to25kt.databinding.ActivitySaveScoreBinding


class ScoreActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySaveScoreBinding.inflate(layoutInflater) }

    var buffer:StringBuffer= StringBuffer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvSavescore1to25.append(G.name + "  =>  " + G.saveTime.toString() + " 초\n")
        binding.tvSavescore199.append(G.save199)

        binding.btnHome.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()}


        buffer.append(G.save199.toString() + "\n")
    }
}