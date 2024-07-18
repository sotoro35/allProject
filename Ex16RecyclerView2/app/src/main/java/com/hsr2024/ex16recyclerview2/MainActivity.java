package com.hsr2024.ex16recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   ArrayList<Item> items= new ArrayList<Item>();

    RecyclerView recyclerView;
   MyAdapter adapter;  //3)번의 객체생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. 대량의 테이터 추가하기
        items.add(new Item("루피","밀집모자해적단 선장",R.drawable.crew_luffy));
        items.add(new Item("조로","밀집모자해적단 부선장",R.drawable.crew_zoro));
        items.add(new Item("나미","밀집모자해적단 항해사",R.drawable.crew_nami));
        items.add(new Item("우솝","밀집모자해적단 저격수",R.drawable.crew_usopp));
        items.add(new Item("상디","밀집모자해적단 요리사",R.drawable.crew_sanji));
        items.add(new Item("쵸파","밀집모자해적단 의사",R.drawable.crew_chopper));
        items.add(new Item("니코로빈","밀집모자해적단 고고학자",R.drawable.crew_nicorobin));
        items.add(new Item("프랑키","밀집모자해적단 조선공",R.drawable.bg_one02));
        items.add(new Item("브록","밀집모자해적단 음악가",R.drawable.bg_one07));
        items.add(new Item("징베","밀집모자해적단 조타수",R.drawable.bg_one04));

        //2. 아이템뷰 1개의 디자인 시안 .xml 모양 레이아웃 설계

        //3. Item데이터의 개수만큼 xml 시안 모양으로 아이템뷰 객체들을 생성해주는 아탑터 클래스 설계 및 객체 생성
        recyclerView= findViewById(R.id.recycler_view);
        adapter= new MyAdapter(this,items);
        recyclerView.setAdapter(adapter); //4. 리사이클뷰와 연결하기





    }
}