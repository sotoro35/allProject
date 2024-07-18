package com.hsr2024.ex37threadnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= findViewById(R.id.iv);

        findViewById(R.id.btn).setOnClickListener(v -> {
            // 네트워크 작업은 과금이 있을 수 있는 기능임. 인터넷 사용에 대한 권한을 먼저 획득해야 함. 권한 작업은 Androidmanifest.xml에서 작업함
            // 네트워크 작업은 오래걸리는 작업으로 판단함. 그래서 반드시 별도 Thread를 사용해야 함.

//            MyThread t= new MyThread();
//            t.start();

            //이미지 로드 라이브러리를 이용하여 서버의 이미지를 불러와서 보여주기
            //Glide.with(this).load("https://cdn.pixabay.com/photo/2017/01/09/00/49/snow-1964361_640.jpg").into(iv);
            //Glide.with(this).load(R.drawable.newyork).into(iv);

            //gif 이미지
           // iv.setImageBitmap(R.drawable.moana); 기존이미지는 움직이는 기능이 없다..
            Glide.with(this).load(R.drawable.moana).into(iv);

        });

    }//onCreadte

    //inner class
    class MyThread extends  Thread{
        @Override
        public void run() {
            // 읽어올 이미지의 인터넷경로 주소 URL
            String imgAddress="https://cdn.pixabay.com/photo/2015/01/07/11/31/tigers-591359_1280.jpg";



            try {
                // 위 주소의 서버까지 무지개로드(Stream)을 열어주는 해임달(URL) 객체 생성
                URL url= new URL(imgAddress);

                //무지개로드 열기
                InputStream is= url.openStream();

                //스트림을 통해 이미지를 읽어와서 이미지 뷰에 설정!
                //이미지파일을 자바에서 읽어오면.. byte[]로 읽어옴. 하지만 byte[]은 이미지뷰에 보여질수 없는 데이터 형식임.
                //byte[]을 스트림을 통해 읽어와서 이미지뷰에 넣을 수 있도록 자바 객체화 시켜야 함.
                //그 객체의 클래스명이 Bitmap 클래스임.
                Bitmap bm= BitmapFactory.decodeStream(is); //Bitmap은 직접 만들수 없다. 그래서 그걸 만들어주는 Bitmapfactory에게 부탁해야 한다.
                //그림을 가진 객체 Bitmap을 이미지뷰에 설정하여 보여주기
               // iv.setImageBitmap(bm); //error
                //별도 Thread는 UI작업이 불가능

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageBitmap(bm);
                    }
                });

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


}