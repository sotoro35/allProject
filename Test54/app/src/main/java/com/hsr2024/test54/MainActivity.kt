package com.hsr2024.test54

import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import java.io.File
import java.util.Date

class MainActivity : AppCompatActivity() {

    val iv:ImageView by lazy { findViewById(R.id.iv) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn() }
    }

    // 촬영된 사진이 저장될 파일의 경로.. 내장저장 - 외부 - 공용
    var imgUri:Uri? = null

    //대행사 등록
    val resultLauncher:ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if ( it.resultCode == RESULT_OK ){
            Glide.with(this).load(imgUri).into(iv)

        }
    }

    private fun clickBtn(){
        val intent:Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        setPhotoUri() // 촬영된 사진을 저장하는 파일의 경로 메소드

        // 촬영한 사진을 저장하려면 택배아저씨한테 경로를 전달해줘야함
        imgUri?.let { intent.putExtra(MediaStore.EXTRA_OUTPUT,it) }

        resultLauncher.launch(intent)

    }

    //촬영 된 이미지 파일의 경로를 만들어주는 메소드
    private fun setPhotoUri(){
        val path:File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) //공용폴더로 경로 설정

        val sdf:SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss") // 날짜+시간
        val fileName:String = "IMG_"+ sdf.format(Date()) +".jpg" // 파일 이름으로 쓸 문자열 변수.. 파일명을 현재의 날짜+시간으로 만든다.
        val file:File = File(path,fileName) // 위에서 만든 경로 + 파일명을 새로운 파일 객체로 생성!

        imgUri= FileProvider.getUriForFile(this,"fileuri",file) // File --> Uri 변경. 카메라앱은 Uri를 원함.
        // 앱끼리 db 공유 불가능, 하지만 제공은 가능함. 그 기능을 provider가 해주는것.
        // provider를 쓰려면 Manifest에 등록해야함.

        //AlertDialog.Builder(this).setMessage(imgUri.toString()).create().show()




    }
}