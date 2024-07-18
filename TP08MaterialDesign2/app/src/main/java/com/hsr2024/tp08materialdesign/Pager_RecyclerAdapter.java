package com.hsr2024.tp08materialdesign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Pager_RecyclerAdapter extends RecyclerView.Adapter<Pager_RecyclerAdapter.VH> {

    Context context;
    ArrayList<Integer> pagerItems; //이미지만 넣을거라 이미지 아이디를 넣을 Integer로 생성 [챗gpt 도움]


    public Pager_RecyclerAdapter(Context context, ArrayList<Integer> pagerItems) {
        this.context = context;
        this.pagerItems = pagerItems;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.recyclerpager,parent,false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Integer item= pagerItems.get(position);
        holder.pager.setImageResource(item);


    }

    @Override
    public int getItemCount() {
        return pagerItems.size();
    }

    static class VH extends RecyclerView.ViewHolder{
        ImageView pager;

        public VH(@NonNull View itemView) {
            super(itemView);
            pager=itemView.findViewById(R.id.viewpager);
        }

    }
}
