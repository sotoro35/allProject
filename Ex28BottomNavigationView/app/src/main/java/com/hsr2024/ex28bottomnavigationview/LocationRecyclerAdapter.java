package com.hsr2024.ex28bottomnavigationview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.VH> {

    Context context;

    ArrayList<LocationRecyclerItem> items;

    public LocationRecyclerAdapter(Context context, ArrayList<LocationRecyclerItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.recycler_item,parent,false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        LocationRecyclerItem item= items.get(position); //리스트클래스를 불러와서 그안의 값을 받는거..
        holder.iv.setImageResource(item.imgId);
        holder.tv.setText(item.title);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VH extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public VH(@NonNull View itemView) {
            super(itemView);
           iv= itemView.findViewById(R.id.iv);
           tv= itemView.findViewById(R.id.tv);

        }
    }

}
