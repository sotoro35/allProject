package com.hsr2024.test52

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class MyAdapter constructor(var context:Context, var imgs:MutableList<Uri>):Adapter<MyAdapter.VH>(){

    inner class VH constructor(itemView:View) : ViewHolder(itemView){
        val iv = itemView.findViewById<ImageView>(R.id.iv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView:View = LayoutInflater.from(context).inflate(R.layout.page,parent,false)
        return VH(itemView)
    }

    override fun getItemCount(): Int {
        return imgs.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        Glide.with(context).load(imgs[position]).into(holder.iv)
    }
}