package com.hsr2024.mlkit10objectdetecionviaphotoappcameraapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.hsr2024.mlkit10objectdetecionviaphotoappcameraapp.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnPhoto.setOnClickListener { clickPhoto() }
        binding.btnCamera.setOnClickListener { clickCamera() }

    }

    private fun clickPhoto(){
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU) resultLauncher.launch(Intent(MediaStore.ACTION_PICK_IMAGES))
        else resultLauncher.launch(Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*"))
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val uri = it.data?.data
            if (uri != null){
                binding.iv.setImageURI(uri)
                // 선택한 이미지에 대한 Object Detection 수행
                detectObjectFromUri(uri)
            }
        }
    }

    private fun setImageUri(){
        // 촬영될 사진이 저장될 파일경로 만들기
        val path:File = getExternalFilesDir("photo")!!
        if (path.exists()){ // 파일은 만들어주지만 폴더는 만들어주지 않으므로... 없으면 폴더를 만들라는거
            path.mkdirs() // s를 붙이면 하위폴더까지 만들어주는거

            val sdf = SimpleDateFormat("yyyyMMddHHmmss")
            val fileName:String = sdf.format(Date())
            val file:File = File(path,fileName)

            // 파일경로를 Uri 경로로 변환
            imgUri = FileProvider.getUriForFile(this,"com.hsr.mlkit10.provider",file) //authority = 중복되면 안되는 이름으로 넣어야한다. 프로바이더를 부르는 이름

        }
    }


    private fun clickCamera(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // 촬영된 사진은 기본적으로 썸네일로 보냄.. 화질 좋은 사진을 얻고 싶다면 파일로 저장해야함
        // 촬영된 사진을 파일로 저장하고 싶다면... 추가데이터를 전달해야함
        // 파일이 저장될 경로 uri를 추가(extra)데이터로 설정해줘야 함
        setImageUri()
        if (imgUri != null) intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri)
        resultLauncherCamera.launch(intent)

    }

    private var imgUri:Uri?= null

    private val resultLauncherCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            if( imgUri != null ){
                binding.iv.setImageURI(imgUri)

                detectObjectFromUri(imgUri!!)
            }else {
                // 인텐트로 실행한 카메라앱은 촬영결과를 파일로 저장하지 않음.
                // 촬영된 사진의 썸네일 이미지를 Bitmap 데이터 전달해줌 [ uri가 아님 ]
                val bm: Bitmap? = it.data?.extras?.getParcelable("data") // bitmap으로 따로 안줌.. 그게 들은 보따리(Parcelable)로 줌.. 식별자는 정해져있음"data"
                binding.iv.setImageBitmap(bm)
            }
        }
    }

    private fun detectObjectFromUri(uri: Uri){
        //1. 입력이미지 준비
        val image: InputImage = InputImage.fromFilePath(this,uri)

        //2. Object Detector 준비
        val options = ObjectDetectorOptions.Builder()
            .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
            .enableMultipleObjects()
            .build()

        val objectDetector =ObjectDetection.getClient(options)

        //3. 이미지 처리 비동기
        objectDetector.process(image).addOnSuccessListener {
            binding.tv.text = "감지된 Object 개수: ${it.size}\n"

            // detected object bounding box drawing
            val bm = (binding.iv.drawable as BitmapDrawable).bitmap
            val bitmap = bm.copy(bm.config,true)
            val canvas = Canvas(bitmap)
            val paint = Paint().apply {
                style = Paint.Style.STROKE
                strokeWidth = 8f
            }

            val colors = arrayOf(Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.CYAN)
            var n = 0
            for ( detectedObject in it ){
                paint.color = colors[n++]
                canvas.drawRect( detectedObject.boundingBox, paint)
            }

            binding.iv.setImageBitmap(bitmap)

        }
    }
}