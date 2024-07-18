package com.hsr2024.ex61sharedata

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.hsr2024.ex61sharedata.databinding.ActivityMainBinding
import java.net.URI

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener { clickBtn1() }
        binding.btn2.setOnClickListener { clickBtn2() }
        binding.btn3.setOnClickListener { clickBtn3() }

    }
    private fun clickBtn1(){
        // 묵시적 인텐트로 데이터를 공유
        val intent= Intent(Intent.ACTION_SEND)

        // 공유할 데이터를 택배기사에게 extra data로 추가해서 보내기
        var value:String = binding.inputLayout.editText!!.text.toString()
        intent.putExtra(Intent.EXTRA_TEXT, value)
        intent.setType("text/plain")


        //startActivity(intent)
        // 여러앱 선택기 모양이 안 이뻐...
        val chooserIntent: Intent = Intent.createChooser(intent,"텍스트 공유")
        startActivity(chooserIntent)
    }

    private fun clickBtn2(){
        //이미지 선택기..
        val intent:Intent =
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU) Intent(MediaStore.ACTION_PICK_IMAGES)
            else Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*")
        resultLauncher.launch(intent)
    }

   private val resultLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
       imgUri= it.data?.data
       Glide.with(this).load(imgUri).into(binding.iv)
   // glide는 null을 받았을때 알아서 다운되지 않도록 하는 기능이 들어 있다.
   // 그래서 if (it.resultCode == RESULT_OK) 조건문을 쓰지 않아도 된다. null이 와도 되니까..
    }

    // 선택한 이미지의 경로 uri
    var imgUri: Uri?= null

    private fun clickBtn3(){
        if (imgUri == null){
            Toast.makeText(this, "공유할 이미지를 먼저 선택하세요", Toast.LENGTH_SHORT).show()
            return
        }

        val intent= Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, imgUri)
        intent.setType("image/*")

        startActivity( Intent.createChooser(intent, "이미지 공유"))

    }
}