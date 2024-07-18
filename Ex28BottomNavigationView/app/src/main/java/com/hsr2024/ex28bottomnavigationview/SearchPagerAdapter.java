package com.hsr2024.ex28bottomnavigationview;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

//단순 뷰들을 보여준다면 그냥 Adapter를 쓰지만... 프래그먼트 전용 아답터는 따로 있다.
public class SearchPagerAdapter extends FragmentStateAdapter {

    Fragment[] fragments= new Fragment[2];

    public SearchPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragments[0]= new SearchTab1Fragment();
        fragments[1]= new SearchTab2Fragment();

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
