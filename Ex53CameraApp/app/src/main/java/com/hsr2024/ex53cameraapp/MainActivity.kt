package com.hsr2024.ex53cameraapp

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


// 카메라로 촬영 후, 바로 사진 가져오는거.. 저장x
class MainActivity : AppCompatActivity() {

    val iv:ImageView by lazy { findViewById(R.id.iv) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn() }
    }

    private fun clickBtn(){
        // Camera 앱 실행하기 위한 택배기사 객체 생성
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // 결과를 받아주는 일련의 작업을 대신해주는 대행사 객체 생성
        resultLauncher.launch(intent)

    }

    val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if ( it.resultCode == RESULT_OK ){
            // 마시멜로우 api26버전 이상의 폰에서는 프로그램을 실행한 카메라앱을 곧바로 촬영 이미지를
            // 파일로 저장하지 않도록 변경되었음.

            // 대신 촬영된 결과 이미지 데이터( byte[] )를 Bitmap 객체로 만들어서 돌려 줌.
            // 이 데이터를 택배기사가 extra data로 가져옴. 일반 택배기사 부터 참조
            val intetn:Intent? = it.data

            // 택배기사가 가져온 Bitmap 데이터를 가진 택배묶음 받기
            val bundle: Bundle? = intetn?.extras

            //데이터 뭉치(bundle)에서 Bitmaip 객체만 뽑아오기
            val bm:Bitmap? = bundle?.get("data") as Bitmap

            // 얻어온 이미지데이터 Bitmap을 이미지뷰에 보여주기
            Glide.with(this).load(bm).into(iv)

            // 보여진 사진의 해상도가 매우 안 좋을 것임.
            // 왜? 카메라앱은 기본적으로  썸네일 이미지만 돌려줌
            // 그래서 크게 보면 화질이 깨져 보임

            // 만약 고해상도의 촬영 이미지를 가지고 싶다면...
            // File로 저장되도록 해야 함
        }

    }

}