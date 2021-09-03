package com.help.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.help.tips.base.BaseFragment;
import com.help.tips.fragment.MapFragment;
import com.help.tips.fragment.LiveBarFragment;
import com.help.tips.fragment.XitujuejinFragment;
import com.help.tips.fragment.SmallTargetFragment;
import com.help.tips.fragment.TimeFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;


/**
 * @author Administrator
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "HomeFragment";

    private View mainView;

    @BindView(R.id.tb_layout)
    TabLayout mTableLayout;
    private ViewPager mViewPager;

    private  List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mTtileList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.fragment_home, container, false);

        mTableLayout = mainView.findViewById(R.id.tb_layout);
        mViewPager = mainView.findViewById(R.id.viewPager);

        mFragmentList.add(new SmallTargetFragment());
        mTtileList.add("小目标");
        mFragmentList.add(new MapFragment());
        mFragmentList.add(new TimeFragment());
        mFragmentList.add(new LiveBarFragment());
        mFragmentList.add(new XitujuejinFragment());
        mTtileList.add("地图");
        mTtileList.add("时间");
        mTtileList.add("直播吧");
        mTtileList.add("稀土掘金");

        mainView.findViewById(R.id.iv_back_logo).setVisibility(View.GONE);

        FragmentManager fragmentManager = getChildFragmentManager();
        HomeFragmentPageAdapter pageAdapter = new HomeFragmentPageAdapter(fragmentManager, 1, mFragmentList, mTtileList);
        mViewPager.setAdapter(pageAdapter);
        mTableLayout.setupWithViewPager(mViewPager);


        TextView tvTitle = mainView.findViewById(R.id.tv_title);
        tvTitle.setText("Tips");

        return mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}