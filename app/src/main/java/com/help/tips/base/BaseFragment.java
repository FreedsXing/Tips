package com.help.tips.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freeds.tool.LogUtils;
import com.help.tips.R;

public abstract class BaseFragment extends Fragment {


    protected View mainView;
    protected Unbinder mUnbinder = null;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        LogUtils.e("---" + getClass().getSimpleName() + "---onCreateView" + layoutId);
//
//        //View view = inflater.inflate(layoutId, container, false);
//          // mUnbinder = ButterKnife.bind(this, view);
//
//        return view;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}