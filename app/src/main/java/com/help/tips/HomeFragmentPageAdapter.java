package com.help.tips;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class HomeFragmentPageAdapter extends FragmentStatePagerAdapter {

    private FragmentManager mManager;
    private List<Fragment> mFragmentList;
    private List<String> mTileList;


    public HomeFragmentPageAdapter(@NonNull FragmentManager manager, int behavior, List<Fragment> fragmentList, List<String> tileList) {
        super(manager, behavior);
        mManager = manager;
        mFragmentList = fragmentList;
        mTileList = tileList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTileList.get(position);
    }
}
