package com.hsr2024.tp_1to25kt

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.hsr2024.tp_1to25kt.databinding.ActivityStage2Binding
import kotlin.random.Random

class Stage2Activity : AppCompatActivity() {

    private val binding by lazy { ActivityStage2Binding.inflate(layoutInflater) }


    private var random= 0
    private var score= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.btnRetry.visibility= View.GONE
        binding.btnSave.visibility= View.GONE

        reset()

        binding.btnSave.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()}

        binding.btnRetry.setOnClickListener { reset() }
        binding.btn199.setOnClickListener { gamestart() }

    }

    private fun reset(){
        random = (1..99).random()
        score = 0

        binding.btnRetry.visibility= View.GONE
        binding.tvTitle.text= "몇번만에 맞출까용~?"
        binding.tv.text= ""
        binding.et199.setText("")
    }//gamestart

    private fun gamestart(){
        var numString = binding.et199.editableText.toString()
        try {
            when{
                numString.toInt() < random -> {
                    binding.tvTitle.text = "$numString 보다 큽니다 $random"
                    score++
                }

                numString.toInt() > random -> {
                    binding.tvTitle.text = "$numString 보다 작습니다"
                    score++
                }

            else  -> {
                binding.tvTitle.text = "축하합니다!"
                binding.tv.text = "정답입니다!\n$score 번 만에 맞췄어요!"
                binding.btnRetry.visibility= View.VISIBLE
                hideKeyboard()
                }
            }

            binding.et199.setText("")
        }catch (e:Exception){
            Toast.makeText(this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show()
        }

    }

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun clickSave(){

        G.clickSave(this)

        }

}