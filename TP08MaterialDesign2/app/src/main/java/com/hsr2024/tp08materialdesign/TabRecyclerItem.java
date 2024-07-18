package com.hsr2024.tp08materialdesign;

import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class TabRecyclerItem {

    //int imgId;

    ArrayList<Integer> imageList; //뷰페이저 부분.. 이미지라서 int로 받아야함

    String name,day,money;

    public TabRecyclerItem(ArrayList<Integer> imageList, String name, String day, String money) {
        this.imageList = imageList;
        this.name = name;
        this.day = day;
        this.money = money;
    }

    public ArrayList<Integer> getImageList() {
        return imageList;
    }
    //ArrayList 안에 있는 내용물을 다 가져와서 리턴 메소드
}
