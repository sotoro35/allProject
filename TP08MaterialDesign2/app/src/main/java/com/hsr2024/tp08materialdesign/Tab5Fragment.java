package com.hsr2024.tp08materialdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

public class Tab5Fragment extends Fragment {

    ArrayList<TabRecyclerItem> items= new ArrayList<>();

    TabRecyclerAdapter adapter;

    RecyclerView recyclerView;

    // bottomsheet가 열려있는 상태로 실행하려고...
    RelativeLayout bottomsheet;
    BottomSheetBehavior<RelativeLayout> behavior;

    ArrayList<Integer> imageList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab2,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView= view.findViewById(R.id.recycler);
        adapter= new TabRecyclerAdapter(getActivity(),items);
        recyclerView.setAdapter(adapter);

        bottomsheet= view.findViewById(R.id.bottomsheet);
        behavior = BottomSheetBehavior.from(bottomsheet);

        behavior.setState(behavior.STATE_EXPANDED);

//        items.add(new TabRecyclerItem(R.drawable.travel05,"aa","1박2일","200000원"));
//        items.add(new TabRecyclerItem(R.drawable.travel05,"bb","1박3일","300000원"));
//        items.add(new TabRecyclerItem(R.drawable.travel05,"cc","1박4일","400000원"));
//        items.add(new TabRecyclerItem(R.drawable.travel05,"dd","1박5일","500000원"));
//        items.add(new TabRecyclerItem(R.drawable.travel05,"ee","1박6일","600000원"));
//        items.add(new TabRecyclerItem(R.drawable.travel05,"ff","1박7일","700000원"));
//        items.add(new TabRecyclerItem(R.drawable.travel05,"gg","1박8일","800000원"));


        imageList.add(R.drawable.img01);
        imageList.add(R.drawable.img02);
        imageList.add(R.drawable.img03);
        imageList.add(R.drawable.img04);
        imageList.add(R.drawable.img05);


        items.add(new TabRecyclerItem(imageList,"aa","1박2일","200000원"));
        items.add(new TabRecyclerItem(imageList,"bb","1박3일","300000원"));
        items.add(new TabRecyclerItem(imageList,"cc","1박4일","400000원"));
        items.add(new TabRecyclerItem(imageList,"dd","1박5일","500000원"));
        items.add(new TabRecyclerItem(imageList,"ee","1박6일","600000원"));
        items.add(new TabRecyclerItem(imageList,"ff","1박7일","700000원"));
        items.add(new TabRecyclerItem(imageList,"gg","1박8일","800000원"));




    }
}
