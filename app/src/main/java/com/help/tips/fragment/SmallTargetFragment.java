package com.help.tips.fragment;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.freeds.toolutil.LogUtils;
import com.help.tips.R;
import com.help.tips.adapter.SmallTargetListAdapter;
import com.help.tips.base.BaseFragment;
import com.help.tips.bean.SmallTargetBean;
import com.help.tips.util.SharedPreferencesUtil;
import com.help.tips.util.SpConfigConstants;
import com.help.tips.util.TimeUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


public class SmallTargetFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "SmallTargetFragment";

    private View mainView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_small_target;
    }

    private Activity mActivity;



    private TextView tvAdd;
    private TextView tvDel;
    private TextView tvUpdate;
    private TextView tvQuery;

    private EditText etName;
    private EditText etContent;

    SmallTargetListAdapter listAdapter;

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

        tvAdd = mainView.findViewById(R.id.tv_add);
        tvAdd.setOnClickListener(this);

        tvDel = mainView.findViewById(R.id.tv_del);
        tvDel.setOnClickListener(this);

        tvUpdate = mainView.findViewById(R.id.tv_update);
        tvUpdate.setOnClickListener(this);

        tvQuery = mainView.findViewById(R.id.tv_query);
        tvQuery.setOnClickListener(this);

        etName = mainView.findViewById(R.id.et_name);
        etContent = mainView.findViewById(R.id.et_content);

        mRecyclerView = mainView.findViewById(R.id.recyclerView);

        //SQLiteDatabase db = LitePal.getDatabase();

        showTodayWeek();

        //第一次创建生成小目标列表
        boolean isHasData = SharedPreferencesUtil.getBoolean(getActivity(), SpConfigConstants.isGenerationSmallTargetData, false);
        LogUtils.e(TAG + "---onCreateView---isHasData=" + isHasData);
        if (!isHasData){
            generationLocalData();
        }


        List<SmallTargetBean> list = LitePal.findAll(SmallTargetBean.class);
        for (int i = 0;  i< list.size(); i++) {
            SmallTargetBean bean = list.get(i);
            LogUtils.e( TAG + "---LitePal---" + bean.getId() + "---" + bean.getTargetName());
        }


        listAdapter = new SmallTargetListAdapter(mActivity, list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(listAdapter);

        return mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.e(TAG + "---onCreateView---");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        LogUtils.e(TAG + "---onHiddenChanged---" + hidden);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtils.e(TAG + "---setUserVisibleHint---" + isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mBoxStore.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:

                Toast.makeText(getActivity(), "增加用户", Toast.LENGTH_SHORT).show();
                // mRecyclerView.notifyAll();

                break;
            case R.id.tv_del:

                Toast.makeText(getActivity(), "删除用户", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_update:

                Toast.makeText(getActivity(), "更新用户", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_query:

                Toast.makeText(getActivity(), "查询用户", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    private void generationLocalData(){

        SmallTargetBean bean1 = new SmallTargetBean();
        bean1.setId(1);
        bean1.setTargetName("每天背单词");
        bean1.setImgUrl(R.drawable.target_english_word);
        bean1.save();

        SmallTargetBean bean2 = new SmallTargetBean();
        bean2.setId(2);
        bean2.setTargetName("今日事今日毕");
        bean2.setImgUrl(R.drawable.target_finish_thing);
        bean2.save();

        SmallTargetBean bean3 = new SmallTargetBean();
        bean3.setId(3);
        bean3.setTargetName("每日学习新知识");
        bean3.setImgUrl(R.drawable.target_study_task);
        bean3.save();

        SmallTargetBean bean4 = new SmallTargetBean();
        bean4.setId(4);
        bean4.setTargetName("赶走坏脾气");
        bean4.setImgUrl(R.drawable.target_tolerate);
        bean4.save();

        SmallTargetBean bean5 = new SmallTargetBean();
        bean5.setId(5);
        bean5.setTargetName("戒掉拖延症");
        bean5.setImgUrl(R.drawable.target_procrastination);
        bean5.save();


        SmallTargetBean bean6 = new SmallTargetBean();
        bean6.setId(6);
        bean6.setTargetName("认真护肤");
        bean6.setImgUrl(R.drawable.target_skin_care);
        bean6.save();

        SmallTargetBean bean7 = new SmallTargetBean();
        bean7.setId(7);
        bean7.setTargetName("保持正能量");
        bean7.setImgUrl(R.drawable.target_positive);
        bean7.save();


        SmallTargetBean bean8 = new SmallTargetBean();
        bean8.setId(8);
        bean8.setTargetName("练出马甲线、腹肌");
        bean8.setImgUrl(R.drawable.target_excise_sit_up);
        bean8.save();


        SmallTargetBean bean9 = new SmallTargetBean();
        bean9.setId(9);
        bean9.setTargetName("每天行走");
        bean9.setImgUrl(R.drawable.target_everyday_wark);
        bean9.save();


        SmallTargetBean bean10 = new SmallTargetBean();
        bean10.setId(10);
        bean10.setTargetName("每日冥想");
        bean10.setImgUrl(R.drawable.target_thought);
        bean10.save();

        SmallTargetBean bean11 = new SmallTargetBean();
        bean11.setId(11);
        bean11.setTargetName("每周看电影");
        bean11.setImgUrl(R.drawable.target_watch_film);
        bean11.save();

        SmallTargetBean bean12 = new SmallTargetBean();
        bean12.setId(12);
        bean12.setTargetName("学习任务");
        bean12.setImgUrl(R.drawable.target_study_task);
        bean12.save();

        SmallTargetBean bean13 = new SmallTargetBean();
        bean13.setId(13);
        bean13.setTargetName("读书");
        bean13.setImgUrl(R.drawable.target_read);
        bean13.save();

        SmallTargetBean bean14 = new SmallTargetBean();
        bean14.setId(14);
        bean14.setTargetName("每天运动");
        bean14.setImgUrl(R.drawable.target_excise_dumbbell);
        bean14.save();

        SmallTargetBean bean15 = new SmallTargetBean();
        bean15.setId(15);
        bean15.setTargetName("吃水果");
        bean15.setImgUrl(R.drawable.target_fruits);
        bean15.save();


        SmallTargetBean bean16 = new SmallTargetBean();
        bean16.setId(16);
        bean16.setTargetName("早睡");
        bean16.setImgUrl(R.drawable.target_sleep_early);
        bean16.save();


        SmallTargetBean bean17 = new SmallTargetBean();
        bean17.setId(17);
        bean17.setTargetName("早起");
        bean17.setImgUrl(R.drawable.target_weakup_early);
        bean17.save();


        SmallTargetBean bean18 = new SmallTargetBean();
        bean18.setId(18);
        bean18.setTargetName("喝水");
        bean18.setImgUrl(R.drawable.target_eat_water);
        bean18.save();


        SharedPreferencesUtil.putBoolean(getActivity(), SpConfigConstants.isGenerationSmallTargetData, true);
    }



    /**
     * 显示今日星期
     */
    private void showTodayWeek() {
        String week = TimeUtil.dateToWeek(System.currentTimeMillis() + "");
        tvMonday = mainView.findViewById(R.id.tv_monday);
        tvTuesday = mainView.findViewById(R.id.tv_tuesday);
        tvWednesday = mainView.findViewById(R.id.tv_wednesday);
        tvThursday = mainView.findViewById(R.id.tv_thursday);
        tvFriday = mainView.findViewById(R.id.tv_friday);
        tvSaturaday = mainView.findViewById(R.id.tv_saturaday);
        tvSunday = mainView.findViewById(R.id.tv_sunday);



        switch (week) {
            case "星期一":
                tvMonday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
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