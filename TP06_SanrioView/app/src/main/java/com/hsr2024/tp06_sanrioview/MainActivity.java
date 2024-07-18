package com.hsr2024.tp06_sanrioview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items= new ArrayList<Item>();

    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items.add(new Item("헬로키티", "쿠키굽고 친구사귀는 것을 좋아함",R.drawable.hellokitty));
        items.add(new Item("마이멜로디", "아몬드파운드 케이크를 좋아함",R.drawable.mymelody));
        items.add(new Item("포차코", "바나나 아이스크림을 좋아함",R.drawable.pochacco));
        items.add(new Item("폼폼푸린", "크림 카라멜 푸딩을 좋아함",R.drawable.purin));
        items.add(new Item("턱시도샘", "해피 단브이에서 드럼 담당",R.drawable.tuxedosam));
        items.add(new Item("케로피", "엄마가 만든 주먹밥을 제일 좋아함",R.drawable.keropp));
        items.add(new Item("배드바츠마루", "초밥과 라멘을 좋아함",R.drawable.badmaru1));
        items.add(new Item("시나모롤", "하늘 날기가 특기인 강아지",R.drawable.cinnamaroll));
        items.add(new Item("쿠로미", "고기와 잘생긴 사람 좋아함",R.drawable.kuromi));

        recyclerView=findViewById(R.id.mainView);
        adapter= new MyAdapter(items,this);
        recyclerView.setAdapter(adapter);

    }
}