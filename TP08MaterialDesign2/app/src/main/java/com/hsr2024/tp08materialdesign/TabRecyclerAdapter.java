package com.hsr2024.tp08materialdesign;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class TabRecyclerAdapter extends RecyclerView.Adapter<TabRecyclerAdapter.VH> {
    Context context;

    ArrayList<TabRecyclerItem> items;

    public TabRecyclerAdapter(Context context, ArrayList<TabRecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.recyclerview,parent,false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        TabRecyclerItem item= items.get(position);

        //각각의 Recycler마다 Adapter 설정해주기
        holder.viewPager.setAdapter(new Pager_RecyclerAdapter(context,item.getImageList()));
        //holder.viewPager.setAdapter();
        holder.name.setText(item.name);
        holder.day.setText(item.day);
        holder.money.setText(item.money);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class VH extends RecyclerView.ViewHolder{

        //ImageView iv;
        ViewPager2 viewPager;
        TextView name,day,money;

        public VH(@NonNull View itemView) {
            super(itemView);

            viewPager= itemView.findViewById(R.id.viewpager); //Recycler안에 있는 뷰페이저가 들어갈 부분
            name= itemView.findViewById(R.id.name);
            day= itemView.findViewById(R.id.day);
            money= itemView.findViewById(R.id.money);
        }
    }
}
