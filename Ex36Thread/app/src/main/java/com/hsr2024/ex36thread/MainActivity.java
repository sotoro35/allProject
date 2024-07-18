package com.hsr2024.ex36thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int num=0;

    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);

        findViewById(R.id.btn).setOnClickListener(v -> {
            // 오래 걸리는 작업 -- 별도 Thread를 만들지 않았을때..
            for (int i=0; i<20; i++){
                num++;
                tv.setText(num+"");
                //TextView는 보여주는게 아닌,, 글씨를 가지는 것.. 그래서 모든 작업이 끝나고 마지막에 자기안의 글씨를 그린다.

                //개발자가 화면 뷰를 이용하지 않고 특정값을 확인해보고 싶다면...
                //또는 코드의 실행에 대한 기록을 남기고 싶다면..
                //이 때 사용하는 것이 로그 Log..
                Log.d("aaa","num: "+num);


                //0.5초 대기
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        findViewById(R.id.btn2).setOnClickListener(v -> {
            // 오래 걸리는 작업(ex, network, DB작업..)
            //을 하는 직원(MyThread) 객체 생성 및 실행
            MyThread t= new MyThread();
            // t.run();  //run()을 직접 호출하면.. Main Thread가 작업해버림
            t.start(); // 자동으로 run() 실행됨 = 스레드는 run()안에 있는것만 스레드가 실행한다. 그 외 메소드는 호출한 스레드가 해버림...

        });

    }

    // inner class
    class MyThread extends Thread{
        // 이 특별한 메소드 안에 있는 코드만 이 스레드가 실행함

        @Override
        public void run() {
            for(int i=0; i<20; i++){
                num++;

                //별도의 Thread는 UI 작업을 수행할 수 없음... 원래는 에러나는게 맞는데.. 이번 버전 확인할예정..
                // tv.setText(num+""); // 직원이 메인에게 부탁하는거라서... 숫자가 올라가는게 보인다..
                //Main Thread에게 UI 작업을 요청해야 함.
                // 요청 방법1. Handler 객체를 이용
                // handler.sendEmptyMessage(0); /방식이 번거로움...핸들러를 만들고.. 메세지를 보내고.. 그래서 2번방법 추천

                // 요청 방법2. 별도의 직원에게 UI 작업 위임장을 부여하는 방법 //익명클래스
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       //이 영역안에서는 UI 작업 가능함
                       tv.setText(num+"");

                   }
               });


                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


            }
        }
    }

    // MainActivity class의 멤버변수
    Handler handler= new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            // 이 영역안에서는 UI 작업이 가능함
            tv.setText(num+"");
        }
    };

}