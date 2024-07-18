package com.hsr2024.ex17viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //page별 데이터를 저장하고 있는 대량의 리스트 멤버변수

    ArrayList<PageItem> pageList= new ArrayList<PageItem>();

    MyPageAdapter adapter;

    ViewPager2 pager;

    Button btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터를 추가하기
        pageList.add(new PageItem(R.drawable.hellokitty01,"Sario #1"));
        pageList.add(new PageItem(R.drawable.keropp02,"Sario #2"));
        pageList.add(new PageItem(R.drawable.kuromi03,"Sario #3"));
        pageList.add(new PageItem(R.drawable.pochacco04,"Sario #4"));
        pageList.add(new PageItem(R.drawable.mymelody05,"Sario #5"));
        pageList.add(new PageItem(R.drawable.purin06,"Sario #5"));
        pageList.add(new PageItem(R.drawable.cinnamaroll07,"Sario #7"));
        pageList.add(new PageItem(R.drawable.tuxedosam08,"Sario #8"));
        pageList.add(new PageItem(R.drawable.badmaru09,"Sario #9"));

        //아답터 객체 생성
        adapter= new MyPageAdapter(this,pageList);

        //아답터뷰  찾아와서 아답터 설정
        //RecyclerView recyclerView= findViewById(R.id.recycler_view);
        //recyclerView.setAdapter(adapter);

        pager= findViewById(R.id.pager);
        pager.setAdapter(adapter);

        btnPrev= findViewById(R.id.btn_prev);
        btnNext= findViewById(R.id.btn_next);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index= pager.getCurrentItem(); //현재 페이지 번호
                pager.setCurrentItem(index -1, false);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index= pager.getCurrentItem();
                pager.setCurrentItem(index+1, true);
            }
        });

    }
}