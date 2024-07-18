package com.hsr2024.croptest

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.hsr2024.croptest.databinding.ActivityMainBinding
import com.takusemba.cropme.OnCropListener
import java.io.File
import java.io.FileOutputStream
import java.util.Date

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btn1.setOnClickListener { imageCrop() }
        binding.btn3.setOnClickListener { clickBtn() }

    }//onCreate

    private fun imageCrop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) resultLauncher.launch(
            Intent(
                MediaStore.ACTION_PICK_IMAGES
            )
        )
        else resultLauncher.launch(Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*"))
    }

    var uri: Uri? = null
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                uri = it.data?.data
                if (uri != null) {
                    binding.iv1.setUri(uri!!)
                    binding.btn2.setOnClickListener { binding.iv1.crop() }

                    binding.iv1.addOnCropListener(object : OnCropListener {
                        override fun onFailure(e: Exception) {
                            Toast.makeText(this@MainActivity, "에러$e", Toast.LENGTH_SHORT).show()
                        }

                        override fun onSuccess(bitmap: Bitmap) {
                            binding.iv2.setImageBitmap(bitmap)
                            cropbitmap = bitmap

                        }
                    })
                }
            }
        }// resultLauncher



    // 촬영될 사진이 저장될 파일의 경로 uri 정보를 저장할 객체의 참조변수
    private var cropbitmap: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
    //lateinit var cropbitmap:Bitmap
    lateinit var file:File

    private fun clickBtn(){

        // 촬영한 이미지를 저장하는 파일의 경로를 만들어주는 기능 메소드를 호출
        saveFile(cropbitmap)
        binding.tv.text = file.toString()

        Log.d("파일경로",file.toString())

        loadFile(file)

    }

    private fun saveFile(bitmap: Bitmap){
        // 내장 저장공간의 외부 저장소 중에서 공용영역에 저장 - 앱을 삭제해도 파일은 남아 있는 영역

        // 공용영역의 경로부터
        val path: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        // 경로는 정해졌으니 저장될 파일의 이름과 확장자 정하기 - 중복되지 않도록 날짜를 이용하여 명명
        val sdf: SimpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
        val fileName:String = "IMG_"+sdf.format(Date()) + ".jpg" //"IMG_20240219143924.jpg

        // 경로와 파일명을 결합
        file = File(path, fileName)

        // stream에서는 경로의 폴더가 있으면 냅두고 없으면 만들는 기능이 있기에.. 미리 만들어둘 필요가 없음
        //file.createNewFile()

        // 파일을 스트림에 넣어줌
        val outputStream = FileOutputStream(file)

        // 비트맵 압축
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream)

        outputStream.flush()
        outputStream.close()


        // 여기까지 경로가 잘 되었는지 확인
        //AlertDialog.Builder(this).setMessage(file.toString()).create().show()

    }

    private fun loadFile(file: File) {
        val fileExist = file.exists()
        Log.d("파일불러오기", "$fileExist")
        if (fileExist) {
            val fileDecode = BitmapFactory.decodeFile(file.toString())
            Log.d("파일디코드", "$fileDecode")
            binding.iv3.setImageBitmap(fileDecode)
        }
    }

}

