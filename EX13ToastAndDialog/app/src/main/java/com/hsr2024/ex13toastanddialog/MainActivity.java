package com.hsr2024.ex13toastanddialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn01;
    Button btn02, btn03, btn04, btn05, btn06;

    EditText dialogEt;
    Button dialogBtn;
    TextView dialogTv;

    //다이얼로그에 보여질 항목 데이터들
    String[] items= new String[]{"Apple","Banana","Orange"};
    boolean[] checkdItems= new boolean[]{true, false, true};

    int checkedItemIndex=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn01= findViewById(R.id.btn01);
        btn02= findViewById(R.id.btn02);
        btn03= findViewById(R.id.btn03);
        btn04= findViewById(R.id.btn04);
        btn05= findViewById(R.id.btn05);

        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast t= Toast.makeText(MainActivity.this,"Hello Toast",Toast.LENGTH_SHORT );
                //익명클래스라서(이너클래스)... this라고 칭하면 익명클래스를 말하는거라서 context 기능을 사용불가.. context는 Activity가 상속 받았으니까
                t.show();
            }
        });

        btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //AlertDialog를 만들어주는 건축가(Builder) 객체 생성
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

                //건축가에게 원하는 모양을 요청
                builder.setTitle("다이얼로그");
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                builder.setMessage("Do you wanna quit?");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast t= Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT);
                        t.show();

                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"취소",Toast.LENGTH_LONG).show();


                    }
                });

                //건축가에게 위 설정한 모양으로 만들어 달라고 요청
                AlertDialog dialog= builder.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.setCancelable(false); // 뒤로가기나 창 옆을 눌러도 창이 꺼지지 않음.
                dialog.show();

            }
        });

        btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("항목형 다이얼로그");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //두번째 파라미터가 클릭한 항복의 위치번호 0, 1, 2, .....
                        Toast.makeText(MainActivity.this, items[which],Toast.LENGTH_SHORT).show();

                    }
                });

                AlertDialog dialog= builder.create();
                dialog.show();


            }
        });

        btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Single choice 형 다이얼로그
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("원하는 과일 1개를 선택하세요");
                builder.setSingleChoiceItems(items, checkedItemIndex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItemIndex= which;

                    }
                });

                builder.setPositiveButton("선택완료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,items[checkedItemIndex],Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog dialog= builder.create();
                dialog.show();

            }
        });

        btn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("원하는 과일들을 모두 선택하세요");
                builder.setMultiChoiceItems(items, checkdItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            checkdItems[which]=isChecked; //true,flase를 넣어라..

                    }
                });
                builder.setPositiveButton("선택완료", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<String> aaa= new ArrayList<String>();


                        //if(checkdItems[0]==true) aaa.add(items[0]);
                        //if(checkdItems[1]==true) aaa.add(items[1);
                        //if(checkdItems[2]) aaa.add(items[2]); //굳이 true를 쓰지 않아도.. 참일경우 실행하니까..

                        for (int i=0; i<3; i++)  {if(checkdItems[i]==true) aaa.add(items[i]);}

                        Toast.makeText(MainActivity.this,aaa.toString(),Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
            }
        });

        btn06= findViewById(R.id.btn06);
        btn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("커스텀 뷰 다이얼로그");

                //다이얼로그가 보여줄 뷰의 모양을 별도의 xml에서 설계...
                builder.setView(R.layout.dialog_my);

                //다이얼로그 객체 만들기
                AlertDialog dialog= builder.create();

                //다이얼로그 보이기
                dialog.show();


                //커스텀뷰로 설정한 dialog_my.xml 문서안에 만든 3개의 뷰를 참조
                dialogEt= dialog.findViewById(R.id.dialog_et);
                dialogBtn= dialog.findViewById(R.id.dialog_btn);
                dialogTv= dialog.findViewById(R.id.dialog_tv);

                dialogBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s=dialogEt.getText().toString();
                        dialogTv.setText(s);
                        dialogEt.setText(""); //버튼을 누르고나서 글씨 초기화
                    }
                });

            }
        });

    }//oncreate method..
}//Main