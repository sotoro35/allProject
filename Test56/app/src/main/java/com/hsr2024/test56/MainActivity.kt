package com.hsr2024.test56

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    val previewView:PreviewView by lazy { findViewById(R.id.preview_view) }
    val iv: ImageView by lazy { findViewById(R.id.iv) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


        val permissionState:Int =checkSelfPermission(Manifest.permission.CAMERA)
        if (permissionState == PackageManager.PERMISSION_DENIED)
        // 권한이 거부되었을때... 재 접속시 권한을 다시 받으라는걸 대행사가 하게끔 한다
            resultLauncher.launch(Manifest.permission.CAMERA)
        else startPreview()




    } // onCreate

    var resultLauncher:ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){
            if (it) startPreview() //미리보기 시작
            else finish()
        }

    private fun startPreview(){
        val cameraProviderFuture= ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(
            { val cameraProvider:ProcessCameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build()
                preview.setSurfaceProvider(previewView.surfaceProvider)

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, CameraSelector.DEFAULT_BACK_CAMERA,preview)


        },ContextCompat.getMainExecutor(this))


    }

}