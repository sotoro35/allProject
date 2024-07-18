package com.hsr2024.tp_1to25kt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.hsr2024.tp_1to25kt.databinding.ActivityStage1Binding
import java.util.Collections

class Stage1Activity : AppCompatActivity() {

    private val binding by lazy { ActivityStage1Binding.inflate(layoutInflater) }

    private val btns:Array<Button> by lazy { arrayOf(
        binding.btn01, binding.btn02, binding.btn03, binding.btn04, binding.btn05,
        binding.btn06, binding.btn07, binding.btn08, binding.btn09, binding.btn10,
        binding.btn11, binding.btn12, binding.btn13, binding.btn14, binding.btn15,
        binding.btn16, binding.btn17, binding.btn18, binding.btn19, binding.btn20,
        binding.btn21, binding.btn22, binding.btn23, binding.btn24, binding.btn25,
    ) }

    var random:MutableList<Int> = mutableListOf()
    var number:Int = 1
    var time = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        number=1
        for (i in 1..25) random.add(i)
        gameStart()
        resetGame()

        binding.btnSave.visibility= View.GONE
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.btnHome.setOnClickListener { gameStart() }



    }

    private fun gameStart(){
        Collections.shuffle(random)
        for (i in 0 until 25) btns[i].text = random.get(i).toString()
        binding.btnHome.visibility= View.GONE

        binding.tvNum.text= "1\n입력하세요!"
        binding.tvTime.text= "0초"

        var timer= object :CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                time++
                binding.tvTime.text= time.toString() + " 초"
            }
            override fun onFinish() {
                binding.tvNum.text = "게임오버.."
                binding.tvTime.text = "다시 도전해봐요!"
                binding.btnHome.visibility= View.VISIBLE
            }
        }.start()

        btns.forEach { button -> button.setOnClickListener {it as Button
            if (it.text.toString().toInt() == number) {
                it.visibility= View.INVISIBLE
                number++
                binding.tvNum.text= number.toString() + "\n입력하세요"

            }

            if(number > 25){
                binding.btnHome.visibility= View.VISIBLE
                number= 1
                timer.cancel()
                binding.tvNum.text= "축하합니다!"
                binding.tvTime.text= "$time 초 걸렸어요!"


            }

        } }// foreach..


    }//gamestart

    private fun resetGame() {
        btns.forEach { button -> button.visibility = View.VISIBLE  }
    }

    private fun clickSave(){

        G.clickSave(this)

        }

}