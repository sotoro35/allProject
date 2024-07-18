package com.hsr2024.ex45kotlinehello

// 주 생성자로 상속받기 - constructor 키워드 생략
class AlbaStudent (name:String, age:Int, major:String, var task:String):Student(name, age, major){
    init {
        println("create AlbaStrudend instance")
    }

    override fun show() {
        //super.show()
        println("이름: $name   나이: $age   전공: $major   업무: $task")
    }
}