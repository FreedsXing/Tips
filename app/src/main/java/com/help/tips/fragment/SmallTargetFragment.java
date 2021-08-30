package com.help.tips.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.freeds.toolutil.LogUtils;
import com.help.tips.R;
import com.help.tips.adapter.SmallTargetListAdapter;
import com.help.tips.base.BaseFragment;
import com.help.tips.bean.SmallTargetItemBean;
import com.help.tips.bean.SmallTargetListBean;
import com.help.tips.util.TimeUtil;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class SmallTargetFragment extends BaseFragment {

    private static final String TAG = "SmallTargetFragment";

    private View mainView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_small_target;
    }

    private Activity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.fragment_small_target, container, false);

        showTodayWeek();

        ArrayList<SmallTargetItemBean> list = new ArrayList<>();
        SmallTargetItemBean bean = new SmallTargetItemBean();
        bean.name = "每周看电影";
        bean.days = "2";
        bean.imgUrl = R.mipmap.ic_launcher;
        list.add(bean);

        SmallTargetItemBean bean2 = new SmallTargetItemBean();
        bean2.name = "学习任务";
        bean2.days = "2";
        bean2.imgUrl = R.mipmap.ic_launcher;
        list.add(bean2);

        SmallTargetItemBean bean3 = new SmallTargetItemBean();
        bean3.name = "看书";
        bean3.days = "2";
        bean3.imgUrl = R.mipmap.ic_launcher;
        list.add(bean3);

        for (int i = 0; i < list.size(); i++) {
            LogUtils.e("TAG", TAG + "---onCreateView---" + list.get(i).name);
        }

        mRecyclerView = mainView.findViewById(R.id.recyclerView);

        SmallTargetListAdapter listAdapter = new SmallTargetListAdapter(mActivity, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(listAdapter);

        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * 显示今日星期
     */
    private void showTodayWeek() {
        String week = TimeUtil.dateToWeek(System.currentTimeMillis() + "");
        tvThursday = mainView.findViewById(R.id.tv_thursday);

        switch (week) {
            case "星期一":
                tvThursday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "星期二":
                tvTuesday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "星期三":
                tvWednesday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "星期四":
                tvThursday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "星期五":
                tvFriday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "星期六":
                tvSaturaday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "星期日":
                tvSunday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            default:
                break;
        }
    }

    @BindView(R.id.tv_monday)
    TextView tvMonday;
    @BindView(R.id.tv_tuesday)
    TextView tvTuesday;
    @BindView(R.id.tv_wednesday)
    TextView tvWednesday;
    @BindView(R.id.tv_thursday)
    TextView tvThursday;
    @BindView(R.id.tv_friday)
    TextView tvFriday;
    @BindView(R.id.tv_saturaday)
    TextView tvSaturaday;
    @BindView(R.id.tv_sunday)
    TextView tvSunday;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

}