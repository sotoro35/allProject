package com.hsr2024.ex71firebasechatting

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.hsr2024.ex71firebasechatting.databinding.MyMsgboxBinding
import com.hsr2024.ex71firebasechatting.databinding.OtherMsgboxBinding

class MessageAdapter (val context:Context, val messageItems:List<MessageItem>) : Adapter<ViewHolder>() {

    // 아이템 뷰 시안 디자인 xml 문서가 2종류이기에 VH도 2종류가 필요함
    inner class VH1(val binding:MyMsgboxBinding) :ViewHolder(binding.root)
    inner class VH2(val binding:OtherMsgboxBinding) :ViewHolder(binding.root)

    // 리사이클러의 아이템뷰 모양을 경우에 따라 다르게 구현하고 싶다면.. viewType을 이용해야 합니다.
    // 현재 번째 아이템의 뷰 타입을 결정하기 위한 콜백메소드가 존재함
    override fun getItemViewType(position: Int): Int {
        return if ( G.nickname == messageItems[position].nickname) 0 else 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(context)

        return if (viewType==0) VH1(MyMsgboxBinding.inflate(layoutInflater,parent,false))
        else VH2(OtherMsgboxBinding.inflate(layoutInflater,parent,false))
    }

    override fun getItemCount(): Int = messageItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item:MessageItem = messageItems[position]

        //holder 가 VH1 or VH2
        if ( item.nickname == G.nickname ){
            val vh= holder as VH1
            holder.binding.tvName.text = item.nickname
            holder.binding.tvMsg.text = item.message
            holder.binding.tvTime.text= item.time
            Glide.with(context).load(item.profileUrl).into(holder.binding.iv)

        } else{
            item.nickname == G.nickname
                val vh= holder as VH2
                holder.binding.tvName.text = item.nickname
                holder.binding.tvMsg.text = item.message
                holder.binding.tvTime.text= item.time
                Glide.with(context).load(item.profileUrl).into(holder.binding.iv)
        }
    }
}