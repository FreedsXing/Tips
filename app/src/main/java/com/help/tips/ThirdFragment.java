package com.help.tips;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.freeds.toolutil.LogUtils;
import com.help.tips.second.step.StepCountService;

public class ThirdFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ThirdFragment";

    TextView tvStartService;
    TextView tvStopService;

    Intent intent;


    private StepCountService.ModelBinder modelBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            modelBinder  = (StepCountService.ModelBinder) service;
            modelBinder.start();
            modelBinder.end();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        LogUtils.e("TAG", TAG + "---onCreateView---");
        tvStartService = view.findViewById(R.id.tv_start_service);
        tvStopService = view.findViewById(R.id.tv_stop_service);

        tvStopService.setOnClickListener(this::onClick);
        tvStartService.setOnClickListener(this::onClick);

         intent = new Intent(getActivity(), StepCountService.class);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.e("TAG", TAG + "---onCreateView---");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_service:
                getActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.tv_stop_service:
                getActivity().unbindService(connection);
                break;
            default:
                break;
        }
    }


}