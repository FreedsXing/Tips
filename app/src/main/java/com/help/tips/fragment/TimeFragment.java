package com.help.tips.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.help.tips.R;

import androidx.fragment.app.Fragment;


public class TimeFragment extends Fragment {

    private View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_time, container, false);
        return mainView;
    }
}