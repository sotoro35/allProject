package com.hsr2024.test60

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hsr2024.test60.databinding.RecyclerViewBinding

class MyAdapter constructor(val context:Context, val itemList:List<Item>): Adapter<MyAdapter.VH>() {

    inner class VH(val binding:RecyclerViewBinding) :ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding= RecyclerViewBinding.inflate(LayoutInflater.from(context),parent,false)
        return VH(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.iv.setImageResource(itemList[position].imgId)
        holder.binding.tv.text = itemList[position].title

        holder.binding.iv.setOnClickListener { Toast.makeText(context,"${itemList[position].title}", Toast.LENGTH_SHORT).show() }

    }
}