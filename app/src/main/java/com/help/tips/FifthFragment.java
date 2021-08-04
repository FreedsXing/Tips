package com.help.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FifthFragment extends BaseFragment {

    private View mainView;

    private TextView tvTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_fifth, container, false);

        tvTitle = mainView.findViewById(R.id.tv_title);
        tvTitle.setText("我");
        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}