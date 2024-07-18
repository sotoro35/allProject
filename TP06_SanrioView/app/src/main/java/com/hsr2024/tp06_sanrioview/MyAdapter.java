package com.hsr2024.tp06_sanrioview;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    ArrayList<Item> items;
    Context context;

    public MyAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View itemView=inflater.inflate(R.layout.my_layout, parent,false);

        VH holder= new VH(itemView);
        return holder;
    }

    int num=0;

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Item item = items.get(position);

        holder.tvName.setText(item.name);
        holder.tvMsg.setText(item.msg);
        holder.iv.setImageResource(item.imgId);


        holder.btn01.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                item.tvnum++;
                holder.tvNum.setText(item.tvnum+"");
            }
        });

        holder.btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.tvnum--;
                holder.tvNum.setText(item.tvnum+"");
                if (item.tvnum <0) {
                    item.tvnum=0;
                    holder.tvNum.setText(item.tvnum+"");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    class VH extends RecyclerView.ViewHolder {

    ImageView iv;
    TextView tvName;
    TextView tvMsg;

    TextView tvNum;
    ImageView btn01;
    ImageView btn02;

    public VH(@NonNull View itemView) {
        super(itemView);
        iv= itemView.findViewById(R.id.iv);
        tvName= itemView.findViewById(R.id.tv_name);
        tvMsg= itemView.findViewById(R.id.tv_msg);
        tvNum= itemView.findViewById(R.id.tv_num);
        btn01= itemView.findViewById(R.id.btniv01);
        btn02= itemView.findViewById(R.id.btniv02);

    }
}
}


