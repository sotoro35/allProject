package com.hsr2024.ex46kotlinrecyclerviewapp

// 데이터 3개를 저장하는 용도의 클래스 - 일반 class와 다르게 .equals()를 사용하면 객체 주소가 아니라 값들을 비고해 줌.
// data를 쓰지 않으면 .equals()사용했을때 String이 아닌것들은 다 주소비교를 하기때문에... data class는 {}쓰지않는다.. 어차피 저장용도라서..
data class Item constructor(var title:String, var message:String, var imgId:Int) // {}안에 쓸게 없으면.. 생략가능..