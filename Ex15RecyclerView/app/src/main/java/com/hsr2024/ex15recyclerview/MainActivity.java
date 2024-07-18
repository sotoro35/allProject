package com.hsr2024.ex15recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    //대량의 데이터를 저장할 리스트 참조변수
    ArrayList<Item> items= new ArrayList<>();

    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recycler_view);

        //리사이클러뷰가 보여줄 대량의 데이터들을 추가하기 [실무에서는 서버에 있는 DB를 읽어오는 경우가 많음]
        items.add( new Item("sam"," Hello android ") );
        items.add( new Item("robin"," Nice to meet you ") );
        items.add( new Item("홍길동"," 안녕하세요 \n 처음뵙겠습니다. ") );
        items.add( new Item("홍길동"," 안녕하세요 \n 처음뵙겠습니다. ") );
        items.add( new Item("홍길동"," 안녕하세요 \n 처음뵙겠습니다. ") );
        items.add( new Item("홍길동"," 안녕하세요 \n 처음뵙겠습니다. ") );
        items.add( new Item("홍길동"," 안녕하세요 \n 처음뵙겠습니다. ") );
        items.add( new Item("홍길동"," 안녕하세요 \n 처음뵙겠습니다. ") );
        items.add( new Item("홍길동"," 안녕하세요 \n 처음뵙겠습니다. ") );

        adapter= new MyAdapter(this, items); // items의 size(갯수)를 Adapter의 public int getItemCount() 메소드가 인식하기 위해서 items와 MyAdapter 연결하는 작업. MyAdapter객체에 데이터를 연걸 작업.
        recyclerView.setAdapter(adapter); // MyAdapter객체에 레이아웃을 연결하는 작업


    }
}