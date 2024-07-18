package com.hsr2024.tp01_1to25m;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //스테이지 1이동
        findViewById(R.id.stage1btn).setOnClickListener(v -> {
            Intent intent = new Intent(this, Stage1.class);
            startActivity(intent);

        });


        //스테이지 2이동
        findViewById(R.id.stage2btn).setOnClickListener(v->{
            Intent intent = new Intent(this, Stage2.class);
            startActivity(intent);

        });


        //기록페이지 이동
        findViewById(R.id.scorebtn).setOnClickListener(v -> {
            Intent intent = new Intent(this, SavePage.class);
            startActivity(intent);


        });
    }
}