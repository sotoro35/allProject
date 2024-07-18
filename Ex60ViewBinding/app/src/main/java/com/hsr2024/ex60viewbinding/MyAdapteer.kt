package com.hsr2024.ex60viewbinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hsr2024.ex60viewbinding.databinding.RecyclerItemBinding

class MyAdapteer constructor(val context:Context, val itemList:MutableList<Item>):Adapter<MyAdapteer.VH>() {

    // 아이템 뷰1개의 자식뷰들의 참조변수를 저장하고 있는 ViewHolder 클래스
    inner class VH constructor(itemview:View): ViewHolder(itemview){
        //자식뷰 참조변수를 모두 만들지 말고.. 바인딩객체 참조변수 1개만 보유
        lateinit var binding: RecyclerItemBinding

        init {
            //이미 뷰객체가 만들어져 있다면.. 그냥 바인딩 객체와 연결만 하면.. 자식뷰 참조값을 모두 보유하게 됨
            binding=RecyclerItemBinding.bind(itemview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView:View = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false)
        return VH(itemView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.tv.text = itemList[position].title
        holder.binding.iv.setImageResource(itemList[position].imgId)
    }
}