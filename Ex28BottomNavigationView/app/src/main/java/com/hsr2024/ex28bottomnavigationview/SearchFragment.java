package com.hsr2024.ex28bottomnavigationview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SearchFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    TabLayout tabLayout;
    ViewPager2 pager;

    SearchPagerAdapter adapter;

    //탭 이름을 넣을 배열변수 만들기
    String[] tabTitle = new String[]{"국내숙소","해외숙소"};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout= view.findViewById(R.id.tab_layout);
        pager= view.findViewById(R.id.pager);

        adapter= new SearchPagerAdapter(getActivity());
        pager.setAdapter(adapter);

        //중재자를 생성. 탭과 뷰를 붙이는... 작업..
        new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //탭 글씨를 넣는부분
                tab.setText(tabTitle[position]);

            }
        }).attach(); // 붙여!


    }
}
