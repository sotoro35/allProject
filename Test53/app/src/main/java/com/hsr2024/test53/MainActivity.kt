package com.hsr2024.test53

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    val iv:ImageView by lazy { findViewById(R.id.iv) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn()}
    }



    // 대행사 등록

    val resultLauncher:ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if ( it.resultCode == RESULT_OK ){

            val intent: Intent? = it.data
            val budle:Bundle? = intent?.extras
            val bitmap:Bitmap? = budle?.get("data") as Bitmap

            Glide.with(this).load(bitmap).into(iv)

        }


    }

    private fun clickBtn(){
        val intent:Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(intent)


    }
}