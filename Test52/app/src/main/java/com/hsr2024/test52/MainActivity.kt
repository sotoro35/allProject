package com.hsr2024.test52

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class MainActivity : AppCompatActivity() {

    val tv:TextView by lazy { findViewById(R.id.tv) }

    val imgs:MutableList<Uri> = mutableListOf()
    val pager:ViewPager2 by lazy { findViewById(R.id.pager) }
    val dots:WormDotsIndicator by lazy { findViewById(R.id.dots_indicator) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn1).setOnClickListener { clickbtn1() }
        findViewById<Button>(R.id.btn2).setOnClickListener { clickbtn2() }
    }

    // 기본 대행사 등록
    val resultLauncher:ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_CANCELED) {

        } else {
            imgs.clear()

            if (it.data?.data != null) {
                imgs.add(it.data?.data!!)

            } else{
                val cnt:Int = it.data?.clipData?.itemCount!!
                for (i in 0 until cnt) it.data?.clipData?.getItemAt(i)
                    ?.let { it1 -> imgs.add( it1.uri) }

            }
            tv.text = "이미지의 걔수 : ${imgs.size}"
            pager.adapter = MyAdapter(this,imgs)
            dots.attachTo(pager)
        }

    }

    private fun clickbtn1(){
        val intent:Intent = Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true)
        resultLauncher.launch(intent)



    }

    // JetPack 대행사 등록
    val pickMultipleLauncher: ActivityResultLauncher<PickVisualMediaRequest> = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()){
        imgs.clear()

        for (uri in it) imgs.add(uri)

        tv.text = "이미지 개수 : ${imgs.size}"
        pager.adapter = MyAdapter(this, imgs)
        dots.attachTo(pager)
    }

    private fun clickbtn2(){
        pickMultipleLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))

    }

}