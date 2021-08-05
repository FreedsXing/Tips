package com.help.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.freeds.toolutil.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SecondFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "SecondFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);

        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText("时间");
        view.findViewById(R.id.iv_back_logo).setVisibility(View.GONE);


        LogUtils.e("---onCreateView---");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        LogUtils.e("TAG" + "---Date获取当前日期时间---" + simpleDateFormat.format(date));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}