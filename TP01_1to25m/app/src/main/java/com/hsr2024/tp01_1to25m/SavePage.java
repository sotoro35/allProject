package com.hsr2024.tp01_1to25m;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SavePage extends AppCompatActivity {

    TextView scoretv,scoretv199;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_page);
        scoretv= findViewById(R.id.scoretv);
        scoretv199=findViewById(R.id.scoretv199);

        Intent intent = getIntent();
        String save = "";
        save= intent.getStringExtra("save");
        save= save+"\n"+save;
        scoretv.setText(save);


        findViewById(R.id.homebtn).setOnClickListener(v -> {
            Intent intent2= new Intent(SavePage.this, MainActivity.class);
            startActivity(intent2);
            finish();
        });
    }
}