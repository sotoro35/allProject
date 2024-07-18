package com.hsr2024.tp06_sanrioview;

//값을 넣어둘 나만의 리스트 만들기
public class Item {

    String name;
    String msg;
    int imgId;
    int tvnum=0;

    public Item(String name, String msg, int imgId) {
        this.name = name;
        this.msg = msg;
        this.imgId = imgId;
    }
}
