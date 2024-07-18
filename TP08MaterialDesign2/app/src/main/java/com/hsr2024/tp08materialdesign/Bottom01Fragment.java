package com.hsr2024.tp08materialdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Bottom01Fragment extends Fragment {

    ViewPager2 pager;
    TabLayout tabLayout;

    MainAdapterFragment adapterFragment;

    AppBarLayout appbar;

    String[] tabTitle= new String[]{"한옥","방","한적한 시골","열대 지역","창작 공간"};

    //필터 눌렀을때 다이얼로그
    FilterDialogFragment dialogFragment;

    //바텀 네비게이션과 프래그먼트 연결 할 참조변수
    BottomNavigationView bnv;
    FragmentManager fragmentManager;
    Fragment[] fragments= new Fragment[4];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottom01,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            pager= view.findViewById(R.id.fragment_pager);
            tabLayout= view.findViewById(R.id.tab);
            adapterFragment = new MainAdapterFragment(getActivity());
            pager.setAdapter(adapterFragment);

            //페이저의 스와이프 기능 해제
            pager.setUserInputEnabled(false);

            new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    tab.setText(tabTitle[position]);

                    switch (position) {
                        case 0:
                            tab.setIcon(R.drawable.baseline_houses_24);
                            break;
                        case 1:
                            tab.setIcon(R.drawable.baseline_bedroom_24);
                            break;
                        case 2:
                            tab.setIcon(R.drawable.baseline_map);
                            break;
                        case 3:
                            tab.setIcon(R.drawable.baseline_brightness_5_24);
                            break;
                        case 4:
                            tab.setIcon(R.drawable.baseline_brush_24);
                            break;
                    }
                }
            }).attach();

        //필터 다이얼로그 설정
        view.findViewById(R.id.filter_iv).setOnClickListener(v -> {
            dialogFragment= new FilterDialogFragment();
            dialogFragment.show(getActivity().getSupportFragmentManager(), "필터");

        });

    }
}
