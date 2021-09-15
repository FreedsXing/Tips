package com.help.tips.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.help.tips.R;
import com.help.tips.base.BaseFragment;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;


public class TimeFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_time, container, false);
        mUnbinder = ButterKnife.bind(this, mainView);


        return mainView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mBoxStore.close();
    }

}