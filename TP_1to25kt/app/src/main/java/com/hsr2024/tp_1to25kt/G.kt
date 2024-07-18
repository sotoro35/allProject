package com.hsr2024.tp_1to25kt

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.hsr2024.tp_1to25kt.databinding.DialogSaveBinding

class G {

    // 기록 저장용 데이터 액티비티끼리 공유하기

    companion object {

        var saveTime = 0
        var save199 = ""
        var name = ""


        fun clickSave(context: Context) {
            val binding = DialogSaveBinding.inflate(LayoutInflater.from(context))

            var dialog= AlertDialog.Builder(context).setView(binding.root).create()
            dialog.show()

            binding.btnDialogSave.setOnClickListener {
                name= binding.etDialogSave.text.toString()
                save199= binding.etDialogSave.text.toString()
                context.startActivity(Intent(context,ScoreActivity::class.java))
                dialog.dismiss()
            }

        }

    }
}