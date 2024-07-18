package com.hsr2024.mtp01_1to25;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] btnss= new int[]{R.id.btn01,R.id.btn02,R.id.btn03,R.id.btn04,R.id.btn05,R.id.btn06,R.id.btn07,R.id.btn08,R.id.btn09,R.id.btn10
            ,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16,R.id.btn17,R.id.btn18,R.id.btn19
            ,R.id.btn20,R.id.btn21,R.id.btn22,R.id.btn23,R.id.btn24,R.id.btn25};

    FrameLayout stage1, stage2, stage3, stage4,stage5, stageend;

    Button startbtn, scorebtn, savebtn, savebtn02, retrybtn, homebtn, hnum199, btn199, btn199s, btn199h, endbtn ;

    EditText saveet, et199;
    Button[] btn= new Button[25];

    TextView timetv01,timetv02,timetv03, scoretitle, scoretv, numtv, tv199, scoretv199;


    //stage2셔플 넣어둘 스트링..
    String nums;
    int randomnum199;

    //누적을 위한 스트링
    String ss="";
    String s199="";
    int num=1;
    int num199=0;


    //타이머 저장 스트링
    String savetime="";
    int time,time2;

    CountDownTimer timer;

    Random random= new Random();

    //키패드 숨기기


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stage1= findViewById(R.id.stage01);
        stage2= findViewById(R.id.stage02);
        stage3= findViewById(R.id.stage03);
        stage4= findViewById(R.id.stage04);
        stage5= findViewById(R.id.stage05);
        stageend= findViewById(R.id.stageend);


        startbtn = findViewById(R.id.startbtn);
        scorebtn = findViewById(R.id.scorebtn);
        savebtn = findViewById(R.id.savebtn);
        retrybtn = findViewById(R.id.retrybtn);
        homebtn = findViewById(R.id.homebtn);
        hnum199 = findViewById(R.id.num199);
        endbtn = findViewById(R.id.endbtn);


        scorebtn.setOnClickListener(allbtn);
        retrybtn.setOnClickListener(allbtn);
        homebtn.setOnClickListener(allbtn);
        hnum199.setOnClickListener(allbtn);
        endbtn.setOnClickListener(allbtn);


        savebtn.setOnClickListener(savebtns);

        timetv01= findViewById(R.id.timetv01);
        timetv02= findViewById(R.id.timetv02);
        timetv03= findViewById(R.id.timetv03);
        tv199= findViewById(R.id.tv199);
        et199= findViewById(R.id.et199);
        btn199= findViewById(R.id.btn199);
        btn199s= findViewById(R.id.btn199s);
        btn199h= findViewById(R.id.btn199h);
        scoretv199= findViewById(R.id.scoretv199);
        scoretitle= findViewById(R.id.scoretitle);
        scoretv= findViewById(R.id.scoretv);
        numtv= findViewById(R.id.numtv);
        btn199s.setOnClickListener(savebtns);
        btn199h.setOnClickListener(stage5btn);

        //stage초기화
        stage1.setVisibility(View.VISIBLE);
        stage2.setVisibility(View.GONE);
        stage3.setVisibility(View.GONE);
        stage4.setVisibility(View.GONE);
        stage5.setVisibility(View.GONE);
        stageend.setVisibility(View.GONE);

        //버튼 대입 및 리스너 연결
        for(int i=0; i<btn.length; i++) {
            btn[i]= findViewById(btnss[i]);
            btn[i].setOnClickListener(btns);
        }

        //셔플 돌리기 위한 숫자 만들어 놓기..
        ArrayList<Integer> random= new ArrayList<>();
        for(int i=1; i<=25; i++) random.add(i);


        //시작하면 stage2 게임 시작.. 셔플도 함께..
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stage1.setVisibility(View.GONE);
                stage2.setVisibility(View.VISIBLE);
                stage3.setVisibility(View.GONE);
                stage4.setVisibility(View.GONE);
                numtv.setText("1");


                time=60;

                for (int i = 0; i < btn.length; i++) btn[i].setVisibility(View.VISIBLE);

                Collections.shuffle(random);

                for (int i = 0; i < 25; i++) {
                    nums = random.get(i).toString();
                    btn[i].setText(nums);
                }

                //카운터 다운...실행..
                timer = new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        time--;
                        timetv01.setText(time+"초\n남았어요! 화이팅!");
                    }

                    @Override
                    public void onFinish() {
                        stage2.setVisibility(View.GONE);
                        stageend.setVisibility(View.VISIBLE);

                    }
                }.start();
            }
        }); //stage2 ...

        findViewById(R.id.s2_homebtn).setOnClickListener(v -> {
            timer.cancel();
            stage2.setVisibility(View.GONE);
            stage1.setVisibility(View.VISIBLE);

        });


        //stage05 숫자입력버튼...
        btn199.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stage5view();
                et199.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });//stage05 숫자입력버튼...


        // 숫자 맞추기 게임에서 입력값을 넣고 핸드폰 키패드 확인 눌렀을때에도 반응하도록..
        // EditText에 OnEditorActionListener 추가
        et199.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // 완료 버튼을 눌렀을 때 수행할 동작
                    stage5view();
                    // 키보드 숨기기
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    et199.setText("");

                    return true;
                }
                return false;
            }
        });


    }//onCreate...

    //점수버튼,다시하기버튼,홈버튼 제어 리스너
    View.OnClickListener allbtn= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == scorebtn ) {
                stage1.setVisibility(View.GONE); //현재 페이지
                stage4.setVisibility(View.VISIBLE); //이동 페이지
            }
            if(v == retrybtn ) {
                stage3.setVisibility(View.GONE);
                stage1.setVisibility(View.VISIBLE);
            }
            if(v == homebtn ) {
                stage4.setVisibility(View.GONE);
                stage1.setVisibility(View.VISIBLE);
            }

            if( v== endbtn ){
                stageend.setVisibility(View.GONE);
                stage1.setVisibility(View.VISIBLE);
            }

            if( v == hnum199 ){
                stage1.setVisibility(View.GONE); //현재 페이지
                stage5.setVisibility(View.VISIBLE); //이동 페이지
                num199=0;
                btn199.setEnabled(true);
                et199.setEnabled(true);
                btn199s.setVisibility(View.GONE);
                tv199.setText("몇번만에 맞출까용~?");

                //랜덤 값 생성
                randomnum199= random.nextInt(100+1);
                //tv199.setText(randomnum199+"");
            }

        }
    }; //allbtn..

    // stage2 버튼 클릭 제어
    View.OnClickListener btns= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                Button btns= (Button) v;
                String s= btns.getText().toString();
                int btn= Integer.parseInt(s);

                if( btn == num ){
                    v.setVisibility(View.INVISIBLE);
                    num++;
                    numtv.setText(String.valueOf(num));

                    if (num > 25) {
                        num=1;
                        time2= 60-time;
                        timetv02.setText(time2+"초\n걸렸어요!");
                        timetv03.setText(time+"초 남았어요!");
                        timer.cancel();
                        stage3();
                     }
                }
            }catch (Exception e){
            }
        }
    };// btns..

    void stage3() {
        stage3.setVisibility(View.VISIBLE);
        stage2.setVisibility(View.GONE);

        }


        //stage05...
        void stage5view(){
            //랜덤값 - num199

            try {
                String s= et199.getText().toString();
                int n= Integer.parseInt(s);

                if( n < randomnum199 ) {
                    tv199.setText(n + " 보다 큽니다");
                    num199++;
                }if( n > randomnum199 ){
                    tv199.setText(n + " 보다 작습니다");
                    num199++;
                }if (n == randomnum199) {
                    num199++;
                    tv199.setText("정답입니다!\n"+num199+"번 만에 맞췄어요");
                    btn199.setEnabled(false);
                    et199.setEnabled(false);
                    btn199s.setVisibility(View.VISIBLE);
                }

            }catch (Exception e){
            }
        }

        View.OnClickListener stage5btn= new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(v == btn199h) {
                    stage5.setVisibility(View.GONE);
                    stage1.setVisibility(View.VISIBLE);
                }

            }
        };

    //다이얼로그 저장버튼들 리스너
    View.OnClickListener savebtns= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
            builder.setView(R.layout.save_dialog);

            AlertDialog savedialog= builder.create();
            savedialog.show();

           if( v == savebtn){
               savebtn02= savedialog.findViewById(R.id.savebtn02);
               saveet= savedialog.findViewById(R.id.saveet);

               savebtn02.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       ss= "[ "+ saveet.getText().toString()+" ]   " + time2+" 초\n" +ss;
                       scoretv.setText(ss);

                       if( !ss.equals("") ){
                           savedialog.dismiss();
                           stage3.setVisibility(View.GONE); //현재 페이지
                           stage4.setVisibility(View.VISIBLE); //이동 페이지
                       }
                   }
               });
           }

           if(v == btn199s) {
               savebtn02= savedialog.findViewById(R.id.savebtn02);
               saveet= savedialog.findViewById(R.id.saveet);

               savebtn02.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       s199= "[ "+ saveet.getText().toString()+" ]  "+num199 +"번\n"+s199;
                       scoretv199.setText(s199);


                       if( !s199.equals("") ){
                           savedialog.dismiss();
                           stage3.setVisibility(View.GONE); //현재 페이지
                           stage4.setVisibility(View.VISIBLE); //이동 페이지
                       }
                   }
               });
           }
        }
    };
}//MainActivity..