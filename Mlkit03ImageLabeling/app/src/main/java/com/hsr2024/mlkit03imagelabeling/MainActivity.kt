package com.hsr2024.mlkit03imagelabeling

import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.hsr2024.mlkit03imagelabeling.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn.setOnClickListener { clickBtn() }
    }

    private fun clickBtn(){

        //#1. 입력이미지 준비
        val bm = (binding.iv.drawable as BitmapDrawable).bitmap
        val image = InputImage.fromBitmap(bm,0)

        //#2. 이미지 라벨러
        val labeler: ImageLabeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        //#3. 이미지 처리
        labeler.process(image).addOnSuccessListener {
            val builder = StringBuilder()
            for( label in it ){
                builder.append("${label.index} : ${label.text} ~ ${label.confidence}\n")

            }
            binding.tv.text = builder.toString()
        }

    }
}