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

import com.freeds.tool.LogUtils;
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
import butterknife.ButterKnife;


public class SmallTargetFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "SmallTargetFragment";


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
        mUnbinder = ButterKnife.bind(this, mainView);

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

        SQLiteDatabase db = LitePal.getDatabase();

        showTodayWeek();

        //????????????????????????????????????
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add:

                Toast.makeText(getActivity(), "????????????", Toast.LENGTH_SHORT).show();
                // mRecyclerView.notifyAll();
                break;
            case R.id.tv_del:

                Toast.makeText(getActivity(), "????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_update:

                Toast.makeText(getActivity(), "????????????", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_query:

                Toast.makeText(getActivity(), "????????????", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    private void generationLocalData(){

        SmallTargetBean bean1 = new SmallTargetBean();
        bean1.setId(1);
        bean1.setTargetName("???????????????");
        bean1.setImgUrl(R.drawable.target_english_word);
        bean1.save();

        SmallTargetBean bean2 = new SmallTargetBean();
        bean2.setId(2);
        bean2.setTargetName("??????????????????");
        bean2.setImgUrl(R.drawable.target_finish_thing);
        bean2.save();

        SmallTargetBean bean3 = new SmallTargetBean();
        bean3.setId(3);
        bean3.setTargetName("?????????????????????");
        bean3.setImgUrl(R.drawable.target_study_task);
        bean3.save();

        SmallTargetBean bean4 = new SmallTargetBean();
        bean4.setId(4);
        bean4.setTargetName("???????????????");
        bean4.setImgUrl(R.drawable.target_tolerate);
        bean4.save();

        SmallTargetBean bean5 = new SmallTargetBean();
        bean5.setId(5);
        bean5.setTargetName("???????????????");
        bean5.setImgUrl(R.drawable.target_procrastination);
        bean5.save();


        SmallTargetBean bean6 = new SmallTargetBean();
        bean6.setId(6);
        bean6.setTargetName("????????????");
        bean6.setImgUrl(R.drawable.target_skin_care);
        bean6.save();

        SmallTargetBean bean7 = new SmallTargetBean();
        bean7.setId(7);
        bean7.setTargetName("???????????????");
        bean7.setImgUrl(R.drawable.target_positive);
        bean7.save();


        SmallTargetBean bean8 = new SmallTargetBean();
        bean8.setId(8);
        bean8.setTargetName("????????????????????????");
        bean8.setImgUrl(R.drawable.target_excise_sit_up);
        bean8.save();


        SmallTargetBean bean9 = new SmallTargetBean();
        bean9.setId(9);
        bean9.setTargetName("????????????");
        bean9.setImgUrl(R.drawable.target_everyday_wark);
        bean9.save();


        SmallTargetBean bean10 = new SmallTargetBean();
        bean10.setId(10);
        bean10.setTargetName("????????????");
        bean10.setImgUrl(R.drawable.target_thought);
        bean10.save();

        SmallTargetBean bean11 = new SmallTargetBean();
        bean11.setId(11);
        bean11.setTargetName("???????????????");
        bean11.setImgUrl(R.drawable.target_watch_film);
        bean11.save();

        SmallTargetBean bean12 = new SmallTargetBean();
        bean12.setId(12);
        bean12.setTargetName("????????????");
        bean12.setImgUrl(R.drawable.target_study_task);
        bean12.save();

        SmallTargetBean bean13 = new SmallTargetBean();
        bean13.setId(13);
        bean13.setTargetName("??????");
        bean13.setImgUrl(R.drawable.target_read);
        bean13.save();

        SmallTargetBean bean14 = new SmallTargetBean();
        bean14.setId(14);
        bean14.setTargetName("????????????");
        bean14.setImgUrl(R.drawable.target_excise_dumbbell);
        bean14.save();

        SmallTargetBean bean15 = new SmallTargetBean();
        bean15.setId(15);
        bean15.setTargetName("?????????");
        bean15.setImgUrl(R.drawable.target_fruits);
        bean15.save();


        SmallTargetBean bean16 = new SmallTargetBean();
        bean16.setId(16);
        bean16.setTargetName("??????");
        bean16.setImgUrl(R.drawable.target_sleep_early);
        bean16.save();


        SmallTargetBean bean17 = new SmallTargetBean();
        bean17.setId(17);
        bean17.setTargetName("??????");
        bean17.setImgUrl(R.drawable.target_weakup_early);
        bean17.save();


        SmallTargetBean bean18 = new SmallTargetBean();
        bean18.setId(18);
        bean18.setTargetName("??????");
        bean18.setImgUrl(R.drawable.target_eat_water);
        bean18.save();


        SharedPreferencesUtil.putBoolean(getActivity(), SpConfigConstants.isGenerationSmallTargetData, true);
    }



    /**
     * ??????????????????
     */
    private void showTodayWeek() {
        String week = TimeUtil.dateToWeek(System.currentTimeMillis() + "");

        switch (week) {
            case "?????????":
                tvMonday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "?????????":
                tvTuesday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "?????????":
                tvWednesday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "?????????":
                tvThursday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "?????????":
                tvFriday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "?????????":
                tvSaturaday.setBackgroundResource(R.drawable.bg_maincolor_circle_edge_2dp);
                break;
            case "?????????":
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