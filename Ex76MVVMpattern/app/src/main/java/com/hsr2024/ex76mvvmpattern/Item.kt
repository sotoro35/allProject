package com.hsr2024.ex76mvvmpattern

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt

class Item {
    var message:ObservableField<String> = ObservableField<String>("Hello")
    var age:ObservableInt = ObservableInt(20)

    fun changeText(){
        message.set("Nice")
        age.set(30)
    }
}