package com.hsr2024.tp10_palragoappclonecoding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hsr2024.tp10_palragoappclonecoding.databinding.HomeFragmentPagerBinding

class FragmentHomeAdapterPager (val context:Context, val pagerList:List<ItemPager>) : Adapter<FragmentHomeAdapterPager.VH>(){

    inner class VH (val binding:HomeFragmentPagerBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding= HomeFragmentPagerBinding.inflate(LayoutInflater.from(context),parent,false)
        return VH(binding)
    }

    override fun getItemCount(): Int = pagerList.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.binding.ivPager.setImageResource(pagerList[position].img)
    }
}