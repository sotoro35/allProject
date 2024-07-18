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
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

public class Tab1Fragment extends Fragment {

    ArrayList<TabRecyclerItem> items= new ArrayList<>();

    TabRecyclerAdapter adapter;

    RecyclerView recyclerView;


    // bottomsheet가 열려있는 상태로 실행하려고...
    RelativeLayout bottomsheet;
    BottomSheetBehavior<RelativeLayout> behavior;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab1,container,false);

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




        // 리사이클러 안의 뷰 페이지 설정 [챗gpt 도움]
        ArrayList<Integer> imageList1 = new ArrayList<>();
        imageList1.add(R.drawable.img01);
        imageList1.add(R.drawable.img02);
        imageList1.add(R.drawable.img03);
        imageList1.add(R.drawable.img04);
        imageList1.add(R.drawable.img05);

        ArrayList<Integer> imageList2 = new ArrayList<>();
        imageList2.add(R.drawable.wedding01);
        imageList2.add(R.drawable.wedding02);
        imageList2.add(R.drawable.wedding03);
        imageList2.add(R.drawable.wedding04);
        imageList2.add(R.drawable.wedding05);


        //recycler뷰가 만들 액자+내용들... imgeList는 뷰페이저.. 이미지덩어리들이라 Integer
        items.add(new TabRecyclerItem(imageList1,"aa","1박2일","200000원"));
        items.add(new TabRecyclerItem(imageList2,"bb","1박3일","300000원"));
        items.add(new TabRecyclerItem(imageList1,"cc","1박4일","400000원"));
        items.add(new TabRecyclerItem(imageList2,"dd","1박5일","500000원"));
        items.add(new TabRecyclerItem(imageList1,"ee","1박6일","600000원"));
        items.add(new TabRecyclerItem(imageList2,"ff","1박7일","700000원"));
        items.add(new TabRecyclerItem(imageList1,"gg","1박8일","800000원"));

        // RecyclerView 어댑터에 데이터 변경을 알림 [챗gpt 도움]
        //이 메소드를 호출함으로써 어댑터는 연결된 RecyclerView에게 데이터셋의 변경 사항을 알리고, RecyclerView는 화면에 표시되는 내용을 최신 상태로 업데이트합니다.
        adapter.notifyDataSetChanged();





        //바텀시트가 내려갈때 바텀네이게이션이 내려가는거.. 챗gpt한테 물어봄...
//        RelativeLayout bottomSheet = view.findViewById(R.id.bottomsheet);
//        BottomSheetBehavior<RelativeLayout> behavior = BottomSheetBehavior.from(bottomSheet);
//
//        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if (getActivity() instanceof MainActivity) {
//                    MainActivity mainActivity = (MainActivity) getActivity();
//                    BottomNavigationView bottomNavigationView = mainActivity.findViewById(R.id.bottom_nv);
//
//                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
//                        // BottomSheet가 완전히 확장되었을 때 BottomNavigationView를 원래 위치로
//                        bottomNavigationView.animate().translationY(0).setDuration(300);
//                    } else if (newState == BottomSheetBehavior.STATE_COLLAPSED || newState == BottomSheetBehavior.STATE_HIDDEN) {
//                        // BottomSheet가 내려가거나 숨겨졌을 때 BottomNavigationView를 화면 밖으로
//                        bottomNavigationView.animate().translationY(bottomNavigationView.getHeight()).setDuration(300);
//                    }
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                // 필요한 경우 슬라이드 중의 동작을 정의 [챗gpt 도움]
//            }
//        });
    }
}
