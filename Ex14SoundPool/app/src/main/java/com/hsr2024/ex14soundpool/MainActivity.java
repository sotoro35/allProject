package com.hsr2024.ex14soundpool;

import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnAgiain, btnGoodjob, btnMusic;

    //사운드 풀 참조변수
SoundPool sp;

    //등록된 음원들의 식별번호를 저장할 변수들
    int sdStart, sdAgain, sdGoodJob, sdMusic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 사운드풀(효과음 재생객체)객체 생성하여 음원들을 등록
        // 사운드풀 객체를 만들어주는 건축가(Builder) 객체 생성
        SoundPool.Builder builder= new SoundPool.Builder();
        builder.setMaxStreams(10); //동시에 플레이 가능한 음원 수 - 10개 넘으면 우선순위가 낮은 소리는 안들림
        sp= builder.build(); // 건축가야 생성해줘

        btnStart= findViewById(R.id.btn_start);
        btnAgiain= findViewById(R.id.btn_again);
        btnGoodjob= findViewById(R.id.btn_good);
        btnMusic= findViewById(R.id.btn_music);

        //음원 등록 하면.. 음원들을 구별하는 식별번호를 리턴해 줌.
        sdStart= sp.load(this,R.raw.s_sijak,0);
        sdAgain= sp.load(this,R.raw.s_again,0);
        sdGoodJob= sp.load(this,R.raw.s_goodjob,0);
        sdMusic= sp.load(this,R.raw.kalimba,0);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(sdStart, 0.8f,0.8f, 3,0,1);
            }
        });

        btnAgiain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(sdAgain, 0.8f,0.8f, 3,0,1);
            }
        });

        btnGoodjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(sdGoodJob, 0.8f,0.8f, 5,1,1);
            }
        });

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.play(sdMusic, 0.8f,0.8f, 7,0,1);
            }
        });

    }
}