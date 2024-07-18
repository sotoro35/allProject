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

public class Page2Fragment extends Fragment {




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_page2,container,false);

        return view;

    }

    ArrayList<Items> iitems = new ArrayList<>();

    MyAdapter adapter;

    ViewPager2 pager;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iitems.add(new Items(R.drawable.hellokitty01,"hellokitty"));
        iitems.add(new Items(R.drawable.keropp02,"keropp"));
        iitems.add(new Items(R.drawable.kuromi03,"kuromi"));
        iitems.add(new Items(R.drawable.pochacco04,"pochacco"));
        iitems.add(new Items(R.drawable.mymelody05,"mymelody"));
        iitems.add(new Items(R.drawable.purin06,"purin"));
        iitems.add(new Items(R.drawable.cinnamaroll07,"cinnamaroll"));
        iitems.add(new Items(R.drawable.tuxedosam08,"tuxedosam"));
        iitems.add(new Items(R.drawable.badmaru09,"badmaru"));

        pager=view.findViewById(R.id.pager);
        adapter= new MyAdapter(getActivity(),iitems);
        pager.setAdapter(adapter);





    }
}
