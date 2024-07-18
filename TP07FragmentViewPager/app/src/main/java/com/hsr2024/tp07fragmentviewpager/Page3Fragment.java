package com.hsr2024.tp07fragmentviewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class Page3Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page3,container,false);
    }


    ArrayList<Items> iitems = new ArrayList<>();

    MyAdapter adapter;

    ViewPager2 pager;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iitems.add(new Items(R.drawable.moana01,"moana01"));
        iitems.add(new Items(R.drawable.moana02,"moana02"));
        iitems.add(new Items(R.drawable.moana03,"moana03"));
        iitems.add(new Items(R.drawable.moana04,"moana04"));
        iitems.add(new Items(R.drawable.moana05,"moana05"));

        pager=view.findViewById(R.id.pager);
        adapter= new MyAdapter(getActivity(),iitems);
        pager.setAdapter(adapter);


    }
}
