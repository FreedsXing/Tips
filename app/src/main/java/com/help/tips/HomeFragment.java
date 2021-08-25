package com.help.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.help.tips.base.BaseFragment;
import com.help.tips.fragment.MapFragment;
import com.help.tips.fragment.TestFragment;
import com.help.tips.fragment.Test3Fragment;
import com.help.tips.fragment.Test4Fragment;
import com.help.tips.fragment.Test5Fragment;
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

        mainView = inflater.inflate(R.layout.fragment_home, container, false);
        //mMapView = (MapView) view.findViewById(R.id.bmapView);

        mTableLayout = mainView.findViewById(R.id.tb_layout);
        mViewPager = mainView.findViewById(R.id.viewPager);

        mFragmentList.add(new TestFragment());
        mTtileList.add("测试");
        mFragmentList.add(new MapFragment());
        mFragmentList.add(new TimeFragment());
        mFragmentList.add(new Test3Fragment());
        mFragmentList.add(new Test4Fragment());
        mFragmentList.add(new Test5Fragment());
        mTtileList.add("地图");
        mTtileList.add("时间");
        mTtileList.add("测试3");
        mTtileList.add("测试4");
        mTtileList.add("测试5");

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