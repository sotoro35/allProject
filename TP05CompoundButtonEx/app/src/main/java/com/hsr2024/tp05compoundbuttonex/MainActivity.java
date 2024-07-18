package com.hsr2024.tp05compoundbuttonex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    EditText num1;
    EditText num2;
    EditText num3;

    RadioGroup rg01;
    RadioGroup rg02;

    CheckBox cb01;
    CheckBox cb02;
    CheckBox cb03;
    CheckBox cb04;

    Button btn;
    ScrollView sv;
    TextView tv;
    String total= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et= findViewById(R.id.et01);
        num1= findViewById(R.id.num1);
        num2= findViewById(R.id.num2);
        num3= findViewById(R.id.num3);
        rg01= findViewById(R.id.rg01);
        rg02= findViewById(R.id.rg02);
        cb01= findViewById(R.id.cb01);
        cb02= findViewById(R.id.cb02);
        cb03= findViewById(R.id.cb03);
        cb04= findViewById(R.id.cb04);
        btn= findViewById(R.id.btn);
        sv= findViewById(R.id.sv);
        tv= findViewById(R.id.tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String s= et.getText().toString();
                    String n1= num1.getText().toString();
                    String n2= num1.getText().toString();
                    String n3= num1.getText().toString();
                    //RadioGroup
                    int checkedId1 = rg01.getCheckedRadioButtonId();
                    int checkedId2 = rg02.getCheckedRadioButtonId();
                    RadioButton rb1 = findViewById(checkedId1); //id를 통해 체크된 RadioButton객체 참조
                    RadioButton rb2 = findViewById(checkedId2); //id를 통해 체크된 RadioButton객체 참조
                    String r1 = rb1.getText().toString();
                    String r2 = rb2.getText().toString();

                    // 체크된 체크박스의 글씨를 얻어오기

//                    if (cb01.isChecked()) c = c + cb01.getText().toString() + " , ";
//                    if (cb02.isChecked()) c = c + cb02.getText().toString() + " , ";
//                    if (cb03.isChecked()) c += cb03.getText().toString() + " , ";
//                    if (cb04.isChecked()) c += cb04.getText().toString(); //본인을 더하라할때 s+ 플러스를 붙이면 된다..

                    ArrayList<String> cbs = new ArrayList<>();
                    ArrayList<String> cbs2 = new ArrayList<>();
                    String c= "";

                    if (cb01.isChecked()) cbs.add(cb01.getText().toString()) ;
                    if (cb02.isChecked()) cbs.add(cb02.getText().toString()) ;
                    if (cb03.isChecked()) cbs.add(cb03.getText().toString()) ;
                    if (cb04.isChecked()) cbs.add(cb04.getText().toString()) ;

                    cbs2.addAll(cbs);
                    c+=cbs;
                    String b= c.replace("[","");;
                    String e= b.replace("]","");


                    total= total+"" +s + "  " + r1 + "  " + r2 + " " + n1 + "-" + n2 + "-" + n3 + "  " + e+"\n";
                    tv.setText(total);

                }catch (Exception e) {


                }
            }
        });

    }
}