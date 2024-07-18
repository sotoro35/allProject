package com.hsr2024.tp07fragmentviewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Page1Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page1,container,false);
    }


    ArrayList<Items> iitems = new ArrayList<>();

    MyAdapter adapter;


    ViewPager2 pager;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iitems.add(new Items(R.drawable.bg_one01,"One Piece01"));
        iitems.add(new Items(R.drawable.bg_one02,"One Piece02"));
        iitems.add(new Items(R.drawable.bg_one03,"One Piece03"));
        iitems.add(new Items(R.drawable.bg_one04,"One Piece04"));
        iitems.add(new Items(R.drawable.bg_one05,"One Piece05"));
        iitems.add(new Items(R.drawable.bg_one06,"One Piece06"));
        iitems.add(new Items(R.drawable.bg_one07,"One Piece07"));

        pager=view.findViewById(R.id.pager);
        adapter= new MyAdapter(getActivity(),iitems);
        pager.setAdapter(adapter);


    }
}
