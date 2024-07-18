package com.hsr2024.ex34activity6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //시스템 앱을 묵시적 인텐트로 실행시켜 보기
        findViewById(R.id.b1).setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction(Intent.ACTION_DIAL);

            //미리 전화번호까지 지정 가능
            Uri uri= Uri.parse("tel:01012345678");
            intent.setData(uri);
            startActivity(intent);
            Log.d("tel",uri.toString());
        });

        findViewById(R.id.b2).setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setAction(Intent.ACTION_SENDTO);

            Uri uri= Uri.parse("smsto:01012345678,01011112222");
            intent.setData(uri);
            intent.putExtra("sms_body","안녕??? ㅎㅎㅎ");

            startActivity(intent);

        });

        findViewById(R.id.b3).setOnClickListener(v -> {
            Intent intent= new Intent(Intent.ACTION_VIEW);
            Uri uri= Uri.parse("https://www.naver.com");
            intent.setData(uri);

            startActivity(intent);

        });

        findViewById(R.id.b4).setOnClickListener(v -> {
            Intent intent= new Intent(Intent.ACTION_PICK);
            intent.setType("image/*"); //MIME type

            startActivity(intent);

        });

        findViewById(R.id.b5).setOnClickListener(v -> {
            Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivity(intent);
        });


    }
}