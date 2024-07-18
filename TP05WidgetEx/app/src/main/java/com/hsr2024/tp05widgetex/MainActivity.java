package com.hsr2024.tp05widgetex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    ImageView[] cvs= new ImageView[10];

    //아이콘 모양의 참조변수
    ImageView imgbtn;
    ImageView icbtns[]= new ImageView[5];

    Toast t;

    //다이얼로그 이미지 참조변수
    ImageView dialog_img, dialog_button;
    int i= 1;

    int k=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //다운받은 CircleImageView의 아이디 저장이 4번까지만 됨.. ㅠㅠ
        for(int i=0; i<cvs.length; i++) {
              cvs[i]=findViewById(R.id.cv01 + i);
//            cvs[i].setImageResource(R.drawable.moana01+k);
//            k++;
//            if(k>4) k=0;
        }

        //5번부터는 팅김 ㅎㅎ..
        //cvs[5].setVisibility(View.INVISIBLE);

        //개별로 넣으면 됨..
        //cvs[7]= findViewById(R.id.cv03);
        //cvs[7].setImageResource(R.drawable.moana03);


        //아이콘 모양 대입
        for (int i=0; i<5; i++){
            icbtns[i]= findViewById(R.id.icbtn01 + i);
            icbtns[i].setOnClickListener(listener);


        }

        imgbtn= findViewById(R.id.imgbtn01);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setView(R.layout.dialog_my);
                AlertDialog dialog= builder.create();
                dialog.show();

                dialog_img= dialog.findViewById(R.id.dialog_img);
                dialog_button= dialog.findViewById(R.id.dialog_button);
                dialog_img.setOnClickListener(listener1);
                dialog_button.setOnClickListener(listener1);


            }
        });
    }// onCreate...



    View.OnClickListener listener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == icbtns[0]) t= Toast.makeText(MainActivity.this, "메뉴바", Toast.LENGTH_SHORT);
            else if (v == icbtns[1]) t= Toast.makeText(MainActivity.this, "토끼", Toast.LENGTH_SHORT);
            else if (v == icbtns[2])  t=Toast.makeText(MainActivity.this, "말풍선", Toast.LENGTH_SHORT);
            else if (v == icbtns[3])  t=Toast.makeText(MainActivity.this, "비행기", Toast.LENGTH_SHORT);
            else  t=Toast.makeText(MainActivity.this, "그림", Toast.LENGTH_SHORT);

            t.show();
        }
    };//listener...

    View.OnClickListener listener1= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
               if(v == dialog_img || v == dialog_button) {
                   dialog_img.setImageResource(R.drawable.image01 + i++);
                   if(i>=5) i=0;
               }
           }
    };//listener1...
}//main...