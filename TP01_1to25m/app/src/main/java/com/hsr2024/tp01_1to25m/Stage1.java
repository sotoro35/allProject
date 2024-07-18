package com.hsr2024.tp01_1to25m;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Stage1 extends AppCompatActivity {

    TextView numtv,timetv;

    Button[] btns = new Button[25];
    int[] btn= new int[]{R.id.btn01,R.id.btn02,R.id.btn03,R.id.btn04,R.id.btn05,R.id.btn06,R.id.btn07,R.id.btn08,R.id.btn09,R.id.btn10
            ,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16,R.id.btn17,R.id.btn18,R.id.btn19
            ,R.id.btn20,R.id.btn21,R.id.btn22,R.id.btn23,R.id.btn24,R.id.btn25};

    ArrayList<Integer> randomnum= new ArrayList<>();

    int num=1;

    int time,time2;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);

        numtv = findViewById(R.id.numtv);
        timetv = findViewById(R.id.timetv);

        numtv.setText("1");
        for (int i=1; i<=25; i++) randomnum.add(i);
        Collections.shuffle(randomnum);

        for (int i=0; i<btns.length; i++){
            btns[i] = findViewById(btn[i]);
            btns[i].setText(""+randomnum.get(i));
            btns[i].setOnClickListener(btnListener);
        }

        start();





    } // on Create



    void start(){

        time=60;

        //카운터 다운...실행..
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                timetv.setText(time+"초\n남았어요! 화이팅!");
            }

            @Override
            public void onFinish() {


            }
        }.start();

    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                    try{
                        Button btns= (Button) v;
                        String s= btns.getText().toString();
                        int btn= Integer.parseInt(s);

                        if( btn == num ){
                            v.setVisibility(View.INVISIBLE);
                            num++;
                            numtv.setText(String.valueOf(num+" 입력"));

                            if (num > 25) {
                                num=1;

                                time2= 60-time;
                                timetv.setText(time2+"초\n걸렸어요!");
                                timetv.setText(time+"초 남았어요!");
                                timer.cancel();
                                clear();
                            }
                        }
                    }catch (Exception e){
                    }


        }
    };

    void clear(){
        Intent intent = new Intent(this, Stage1Clear.class);
        intent.putExtra("time",time2);
        startActivity(intent);

        //finish();

    }

}

