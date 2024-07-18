package com.hsr2024.tp01_1to25m;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Stage1Clear extends AppCompatActivity {

    TextView timetv01,timetv02;

    Button savebtn,savebtn02;
    EditText saveet;

    String ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1_clear);

        timetv01 = findViewById(R.id.timetv01);
        timetv02 = findViewById(R.id.timetv02);

        Intent intent = getIntent();
        int time = intent.getIntExtra("time", -1);

        timetv01.setText(time + "");

        findViewById(R.id.savebtn).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Stage1Clear.this);
            builder.setView(R.layout.save_dialog);

            AlertDialog savedialog = builder.create();
            savedialog.show();

            savebtn02 = savedialog.findViewById(R.id.savebtn02);
            saveet = savedialog.findViewById(R.id.saveet);

            savebtn02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ss = "[ " + saveet.getText().toString() + " ]   " + time + " 초";
                        if (!ss.equals("")) savedialog.dismiss();
                        save();

                    }
            });
        });

        findViewById(R.id.retrybtn).setOnClickListener(v -> finish());
    }

    void save(){
        Intent intent = new Intent(Stage1Clear.this, SavePage.class);
        intent.putExtra("save",ss);
        startActivity(intent);
        finish();
    }
}