package com.hsr2024.test51

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    val tv:TextView by lazy { findViewById(R.id.tv) }
    val iv:ImageView by lazy { findViewById(R.id.iv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn1).setOnClickListener { clickBtn1() }
        findViewById<Button>(R.id.btn2).setOnClickListener { clickBtn2() }
        findViewById<Button>(R.id.btn3).setOnClickListener { clickBtn3() }
    }

    // 멤버변수로 대행사 등록하기
    val resultLauncher:ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if ( it.resultCode == RESULT_OK ){

            val intent:Intent? = it.data
            val uri:Uri? = intent?.data

            tv.text = uri.toString()
            Glide.with(this).load(uri).into(iv)
        }
    }

    private fun clickBtn1(){
        val intent:Intent = Intent(Intent.ACTION_PICK).setType("image/*")
        resultLauncher.launch(intent)

    }

    private fun clickBtn2(){
        val intent:Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*")
        resultLauncher.launch(intent)

    }

    // JetPack 대행사
    val pickMediaResultLauncher: ActivityResultLauncher<PickVisualMediaRequest> = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){
        tv.text = it.toString()
        Glide.with(this).load(it).into(iv)
    }

    private fun clickBtn3(){
        pickMediaResultLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))


    }
}