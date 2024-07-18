package com.hsr2024.ex15recyclerview;

public class Item {
    //Arraylist는 1개의 타입만 가능해서.. 여러개의 타입이 들어있는 클래스를 만들어서 이용한다

    String name; //이름
    String msg; //메세지

    //생성자 메소드 - new 할때 자동으로 호출되는 콜백 메소드
    public Item(String name, String msg){
        //매개변수로 받은 값을 멤버변수에 대입
        this.name= name;
        this.msg =msg;
    }
    public Item(){
        //매개변수로 받은 값을 멤버변수에 대입
        this.name= "";
        this.msg ="";
    }
    }

