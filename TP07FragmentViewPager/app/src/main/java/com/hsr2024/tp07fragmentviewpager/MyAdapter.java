package com.hsr2024.tp07fragmentviewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    Context context;
    ArrayList<Items> items;

    public MyAdapter(Context context, ArrayList<Items> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_pager,parent,false);

        VH holder= new VH(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Items item= items.get(position);

        holder.img.setImageResource(item.img);
        holder.tv.setText(item.text);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class VH extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv;
        public VH(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.iv);
            tv= itemView.findViewById(R.id.tv);
        }
    }
}
