package com.hsr2024.exdatabinding

import androidx.databinding.ObservableField

class User {
    var name:ObservableField<String> = ObservableField<String>("sam")
    var age:Int = 25

    fun changeName(){
        name.set("robin")
    }
}