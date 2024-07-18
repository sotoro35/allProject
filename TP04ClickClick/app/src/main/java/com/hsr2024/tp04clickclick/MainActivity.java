package com.hsr2024.tp04clickclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearbg;
    TextView tv;
    ImageView start;
    ImageView[] images = new ImageView[12];
    ImageView[] ran = new ImageView[12];
    //현재 눌러야할 번호를 저장하고 있는 변수
    int num = 0;
    int stage=1;
    ArrayList<Integer> ranimg = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearbg = findViewById(R.id.linearbg);
        tv = findViewById(R.id.tv);
        start = findViewById(R.id.strat);


        //셔플
        for (int i = 0; i < 12; i++) {
            ranimg.add(i);
        } Collections.shuffle(ranimg);

        // 이미지와 참조변수 연결.. 및 랜덤 이미지 넣기..
        for (int i = 0; i < 12; i++) {
            images[i] = findViewById(R.id.btn01 + i);
            ran[i] = findViewById(R.id.btn01 + i);
            images[i].setOnClickListener(listener);
            ran[i].setImageResource(R.drawable.num01 + ranimg.get(i));
            ran[i].setTag(ranimg.get(i));
            images[i] = ran[i];
            images[i].setVisibility(View.INVISIBLE);
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("숫자를 순서대로 입력하세요");
                linearbg.setBackgroundResource(R.drawable.bg1);
                start.setImageResource(R.drawable.ing);
                for(int i=0; i<12; i++) images[i].setVisibility(View.VISIBLE);
                start.setEnabled(false);

            }
        }); //start listener..
    }//onCreate method..

    //숫자 이미지를 눌렀을때 반응할 리스너..
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String s = v.getTag().toString();
            int n = Integer.parseInt(s);

            if (n == num) {
                v.setVisibility(View.INVISIBLE);
                num++;
            }
            if(num >11){
                num=0;
                stage++;
                if(stage == 2) stage2();
                if(stage == 3) stage3();
                if(stage == 4) end();
            }
        }
    };//listener ...

    public void stage2(){
        Collections.shuffle(ranimg);
        linearbg.setBackgroundResource(R.drawable.bg2);
        tv.setText("알파벳 순서대로 누르세요");

        for(int i=0; i<12; i++) {
            images[i].setVisibility(View.VISIBLE);
            ran[i].setImageResource(R.drawable.alpa01 + ranimg.get(i));
            ran[i].setTag(ranimg.get(i));
            images[i] = ran[i];
            images[i].setOnClickListener(listener);
        }
    } //stage2..

    public void stage3(){
        Collections.shuffle(ranimg);
        linearbg.setBackgroundResource(R.drawable.bg3);
        tv.setText("십이지신 순서대로 누르세요");

        for(int i=0; i<12; i++) {
            images[i].setVisibility(View.VISIBLE);
            ran[i].setImageResource(R.drawable.cha01 + ranimg.get(i));
            ran[i].setTag(ranimg.get(i));
            images[i] = ran[i];
            images[i].setOnClickListener(listener);
        }
    } //stage3

    public void end(){
        tv.setText("축하합니다! 다시할래요?");
        linearbg.setBackgroundResource(R.drawable.end);
        start.setImageResource(R.drawable.start);
        start.setEnabled(true);
        Collections.shuffle(ranimg);
        stage=1;
        for(int i=0; i<12; i++) {
            ran[i].setImageResource(R.drawable.num01 + ranimg.get(i));
            ran[i].setTag(ranimg.get(i));
            images[i] = ran[i];
        }
      }
    }
