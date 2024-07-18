package com.hsr2024.tp08materialdesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //질묵목록..
    //1. 탭1 화면에서 하단툴바 스크롤시 사라지게
    //2. 바텀네비게이션이.. 사실은 플래그먼트안의 바텀시트에 들어있는게 아닌거 같음.. 메인의 바텀네비게이션을 프래그먼트의 바텀시트를 내릴때 같이 내려가도록 구현해야함
    //게다가 바텀시트 내릴때 지도플로팅은 사라짐 ==> 질문함.. 답은 스크롤 리스터 커스텀임


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
    Fragment[] fragments= new Fragment[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        pager= findViewById(R.id.fragment_pager);
//        tabLayout= findViewById(R.id.tab);
//        adapterFragment = new MainAdapterFragment(this);
//        pager.setAdapter(adapterFragment);
//
//        //페이저의 스와이프 기능 해제
//        pager.setUserInputEnabled(false);
//
//        new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy() {
//            @Override
//            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
//                tab.setText(tabTitle[position]);
//
//                switch (position) {
//                    case 0:
//                        tab.setIcon(R.drawable.baseline_houses_24);
//                        break;
//                    case 1:
//                        tab.setIcon(R.drawable.baseline_bedroom_24);
//                        break;
//                    case 2:
//                        tab.setIcon(R.drawable.baseline_map);
//                        break;
//                    case 3:
//                        tab.setIcon(R.drawable.baseline_brightness_5_24);
//                        break;
//                    case 4:
//                        tab.setIcon(R.drawable.baseline_brush_24);
//                        break;
//                }
//            }
//        }).attach();


        //appbar= findViewById(R.id.appbar);
        // 메인화면의 '게스트 선호' 숙소 자세히 알아보기 부분을 첫번째 탭에만 보여주고 싶을때...
//        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                // 첫 번째 탭(인덱스 0)에서만 AppBar를 보이게 하고, 나머지에서는 숨깁니다.
//                if (position == 0) appbar.setVisibility(View.VISIBLE);
//                else appbar.setVisibility(View.GONE);
//            }
//        });

        //필터 다이얼로그 설정
//        findViewById(R.id.filter_iv).setOnClickListener(v -> {
//            dialogFragment= new FilterDialogFragment();
//            dialogFragment.show(getSupportFragmentManager(),"필터");
//
//        });

        // 바텀네비게이션과 프래그먼트들 연결하기
        fragmentManager=getSupportFragmentManager();

        fragments[0] = new Bottom01Fragment();
        fragments[1] = new Bottom02Fragment();
        fragments[2] = new Bottom03Fragment();
        fragments[3] = new Bottom04Fragment();
        fragments[4] = new Bottom05Fragment();

        fragmentManager.beginTransaction().add(R.id.main_container,fragments[0]).commit();

        bnv =findViewById(R.id.bottom_nv);

        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.bnv_search)  fragmentManager.beginTransaction().replace(R.id.main_container,fragments[0]).commit();
                else if (item.getItemId() == R.id.bnv_list)  fragmentManager.beginTransaction().replace(R.id.main_container,fragments[1]).commit();
                 else if (item.getItemId() == R.id.bnv_travel) fragmentManager.beginTransaction().replace(R.id.main_container,fragments[2]).commit();
                 else if (item.getItemId() == R.id.bnv_message) fragmentManager.beginTransaction().replace(R.id.main_container,fragments[3]).commit();
                else if (item.getItemId() == R.id.bnv_login) fragmentManager.beginTransaction().replace(R.id.main_container,fragments[4]).commit();

                return true; // UI 변경시 무조건 true!!!!
            }
        });

        //bnv.setSelectedItemId(R.id.bnv_search);


    }
}