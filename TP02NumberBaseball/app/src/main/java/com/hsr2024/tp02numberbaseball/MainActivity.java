package com.hsr2024.tp02numberbaseball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;
    Button btn;
    TextView tv;

    int num1;
    int num2;
    int num3;

    int ball=0;
    int strike=0;

    String sss= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        btn = findViewById(R.id.btn);
        tv = findViewById(R.id.tv);

        Random ran = new Random();
        num1 = ran.nextInt(10);
        num2 = ran.nextInt(10);
        num3 = ran.nextInt(10);

        if (num1 == num2 | num1 == num3) {
            num2 = ran.nextInt(10);
            num3 = ran.nextInt(10);
            if (num2 == num3) {
                num3 = ran.nextInt(10);
            }
        }

        tv.setText(num1+""+num2+""+num3);


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String s1 = et1.getText().toString();
                    String s2 = et2.getText().toString();
                    String s3 = et3.getText().toString();
                    et1.setText("");
                    et2.setText("");
                   et3.setText(""); //버튼을 누른 뒤 입력한 숫자 사라지게..



                    int ball1 = Integer.parseInt(s1);
                    int ball2 = Integer.parseInt(s2);
                    int ball3 = Integer.parseInt(s3);


//                    if(s1.equals("") ) {
//                    }else if (s2.equals("")) {
//                    }else if(s3.equals("")){
//                    }else {

                    ball=0;
                        if (ball1 == num1 || ball1 == num2 || ball1 == num3){
                            ball++;
                        } if (ball2 == num1 || ball2 == num2 || ball == num3) {
                            ball++;
                        }if(ball3 == num1 || ball3 == num2 || ball3 == num3){
                            ball++;
                        }

                    strike=0;
                        if (ball1 == num1){
                            strike++; ball--;
                        }if(ball2 == num2){
                            strike++; ball--;
                    }if (ball3 == num3){
                            strike++; ball--;
                    }


                        sss=(strike+"strike"+ ball+"ball"+"\n"+sss+"\n");
                    tv.setText(num1+""+num2+""+num3+"\n"+sss);



                }
            });


    }
}