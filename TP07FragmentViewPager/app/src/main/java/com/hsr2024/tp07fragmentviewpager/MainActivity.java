package com.hsr2024.tp07fragmentviewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    ViewPager2 mainPageer;

    MainMyAdapter adapter;

    TabLayout tab;

    String[] tabTitle= new String[]{"원피스","산리오","모아나"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPageer= findViewById(R.id.main_pager);
        adapter= new MainMyAdapter(this);
        mainPageer.setAdapter(adapter);

        tab= findViewById(R.id.tab);


new TabLayoutMediator(tab, mainPageer, new TabLayoutMediator.TabConfigurationStrategy() {
    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

        tab.setText(tabTitle[position]);}
}).attach();


    }
}