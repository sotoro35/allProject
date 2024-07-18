package com.hsr2024.ex60viewbinding

// 아이템 1개의 데이터를 저장하는 클래스 data - 객체끼리 비교했을때 값들이 비교됨... data가 안붙으면 주소를 비교하게됨
data class Item constructor(var imgId:Int,var title:String)