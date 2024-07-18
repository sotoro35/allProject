package com.hsr2024.tp08materialdesign;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainAdapterFragment extends FragmentStateAdapter {

    Fragment[]fragments = new Fragment[5];


    public MainAdapterFragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
        fragments[3] = new Tab4Fragment();
        fragments[4] = new Tab5Fragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments[position];
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }
}
