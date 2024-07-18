package com.hsr2024.ex46kotlinrecyclerviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //뷰 참조변수
    val recyclerView:RecyclerView by lazy { findViewById(R.id.recycler_view) }

    //대량의 데이터를 저장하는 리스트
    var itemList:MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //데이터 추가하기.. --실무에서는 서버나 DB에서 값을 읽어옴.
        loadData()

        // 리사이클러뷰에 보여줄 아이템뷰를 만들어주는 아답터 객체 생성 및 설정
        recyclerView.adapter= MyAdapter(this,itemList)

        // 리사이클러뷰의 아이템 배치를 관리하는 관리자 설정
        recyclerView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true) //reverse - 가장 최근것이 가장 먼저 보이게



    }//onCreate..

    //대량의 데이터를 추가하는 메소드
    private fun loadData(){
        itemList.add(Item("sam","Hello.kotlin",R.drawable.moana01))
        itemList.add(Item("robin","Nice.kotlin",R.drawable.moana02))
        itemList.add(Item("hong","Good.kotlin",R.drawable.moana03))
        itemList.add(Item("rosa","Hello.Java",R.drawable.moana04))
        itemList.add(Item("kim","Nice.Java",R.drawable.moana05))
        itemList.add(Item("lee","Good.Java",R.drawable.moana01))
        itemList.add(Item("gon","have a nice day",R.drawable.moana02))
    }

}//Main...