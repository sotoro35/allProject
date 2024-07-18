package com.hsr2024.test55

import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaPlayer.OnPreparedListener
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val vv:VideoView by lazy { findViewById(R.id.vv) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener { clickBtn() }
    }

    private fun clickBtn(){
        val intent =Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        resultLauncher.launch(intent)
    }

    var resultLauncher:ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val uri:Uri? = it.data?.data

            vv.setVideoURI(uri)
            vv.setMediaController(MediaController(this))
            vv.setOnPreparedListener(object :OnPreparedListener{
                override fun onPrepared(mp: MediaPlayer?) {
                    vv.start()
                }
            })

        }
    }

}