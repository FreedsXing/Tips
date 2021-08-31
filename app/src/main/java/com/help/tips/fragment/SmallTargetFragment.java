package com.help.tips.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.help.tips.bean.SmallTargetItemBean;
import com.help.tips.objectbox.ObjectBox;
import com.help.tips.objectbox.SnowflakeIdGenerator;
import com.help.tips.util.NameUtils;
import com.help.tips.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;


public class SmallTargetFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "SmallTargetFragment";

    private View mainView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_small_target;
    }

    private Activity mActivity;



    private BoxStore mBoxStore;
    private SnowflakeIdGenerator mIdWorker;
    private List<SmallTargetBean> mList;
    private Box<SmallTargetBean> mBox;


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

        //initUserBox();

        //initData();

        showTodayWeek();

        generationLocalData();

        listAdapter = new SmallTargetListAdapter(mActivity, mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(listAdapter);

        return mainView;
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
                String etNameStr = etName.getText().toString();
                String etContentStr = etContent.getText().toString();


                SmallTargetBean user = new SmallTargetBean();
                user.setUserId(String.valueOf(mIdWorker.nextId()));

                if (TextUtils.isEmpty(etNameStr) || TextUtils.isEmpty(etContentStr)){
                    Toast.makeText(getActivity(), "增加用户内容缺失", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    user.setUserName(etNameStr);
                    user.setContent(etContentStr);

                }
                //user.setUserName(NameUtils.createRandomZHName(new Random().nextInt(4) + 1));

                // 插入
                mBox.put(user);
                mList.add(user);

                queryAllData();
                Toast.makeText(getActivity(), "增加用户", Toast.LENGTH_SHORT).show();
                // mRecyclerView.notifyAll();

                break;
            case R.id.tv_del:
                SmallTargetBean user2 = mList.get(mList.size() - 1);

                //删除最末
                mBox.remove(user2);

                queryAllData();

                Toast.makeText(getActivity(), "删除用户", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_update:
                SmallTargetBean user3 = mList.get(mList.size() - 1);
                user3.setUserName(NameUtils.createRandomZHName(new Random().nextInt(4) + 1));

                //更新最末
                mBox.put(user3);

                queryAllData();

                Toast.makeText(getActivity(), "更新用户", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_query:
                // mUserAdapter.setNewData(new ArrayList<User>());

                tvQuery.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        queryAllData();
                    }
                }, 1000);

                Toast.makeText(getActivity(), "查询用户", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    private void generationLocalData(){
        mList = new ArrayList<>();


        SmallTargetBean bean1 = new SmallTargetBean();
        bean1.setUserName("每天背单词");
        bean1.setImgUrl(R.drawable.target_english_word);
        mList.add(bean1);

        SmallTargetBean bean2 = new SmallTargetBean();
        bean2.setUserName("今日事今日毕");
        bean2.setImgUrl(R.drawable.target_finish_thing);
        mList.add(bean2);

        SmallTargetBean bean3 = new SmallTargetBean();
        bean3.setUserName("每日学习新知识");
        bean3.setImgUrl(R.drawable.target_study_task);
        mList.add(bean3);

        SmallTargetBean bean4 = new SmallTargetBean();
        bean4.setUserName("赶走坏脾气");
        bean4.setImgUrl(R.drawable.target_tolerate);
        mList.add(bean4);

        SmallTargetBean bean5 = new SmallTargetBean();
        bean5.setUserName("戒掉拖延症");
        bean5.setImgUrl(R.drawable.target_procrastination);
        mList.add(bean5);

        SmallTargetBean bean6 = new SmallTargetBean();
        bean6.setUserName("认真护肤");
        bean6.setImgUrl(R.drawable.target_skin_care);
        mList.add(bean6);

        SmallTargetBean bean7 = new SmallTargetBean();
        bean7.setUserName("保持正能量");
        bean7.setImgUrl(R.drawable.target_positive);
        mList.add(bean7);

        SmallTargetBean bean8 = new SmallTargetBean();
        bean8.setUserName("练出马甲线、腹肌");
        bean8.setImgUrl(R.drawable.target_excise_sit_up);
        mList.add(bean8);

        SmallTargetBean bean9 = new SmallTargetBean();
        bean9.setUserName("每天行走");
        bean9.setImgUrl(R.drawable.target_everyday_wark);
        mList.add(bean9);

        SmallTargetBean bean10 = new SmallTargetBean();
        bean10.setUserName("每日冥想");
        bean10.setImgUrl(R.drawable.target_thought);
        mList.add(bean10);

        SmallTargetBean bean11 = new SmallTargetBean();
        bean11.setUserName("每周看电影");
        bean11.setImgUrl(R.drawable.target_watch_film);
        mList.add(bean11);

        SmallTargetBean bean12 = new SmallTargetBean();
        bean12.setUserName("学习任务");
        bean12.setImgUrl(R.drawable.target_study_task);
        mList.add(bean12);

        SmallTargetBean bean13 = new SmallTargetBean();
        bean13.setUserName("读书");
        bean13.setImgUrl(R.drawable.target_read);
        mList.add(bean13);

        SmallTargetBean bean14 = new SmallTargetBean();
        bean14.setUserName("每天运动");
        bean14.setImgUrl(R.drawable.target_excise_dumbbell);
        mList.add(bean14);

        SmallTargetBean bean15 = new SmallTargetBean();
        bean15.setUserName("吃水果");
        bean15.setImgUrl(R.drawable.target_fruits);
        mList.add(bean15);

        SmallTargetBean bean16 = new SmallTargetBean();
        bean16.setUserName("早睡");
        bean16.setImgUrl(R.drawable.target_sleep_early);
        mList.add(bean16);

        SmallTargetBean bean17 = new SmallTargetBean();
        bean17.setUserName("早起");
        bean17.setImgUrl(R.drawable.target_weakup_early);
        mList.add(bean17);

        SmallTargetBean bean18 = new SmallTargetBean();
        bean18.setUserName("喝水");
        bean18.setImgUrl(R.drawable.target_eat_water);
        mList.add(bean18);
    }





    private void initUserBox() {

        mBoxStore = ObjectBox.get();
        mBox = mBoxStore.boxFor(SmallTargetBean.class);
    }


    private void queryAllData() {
        mList = mBox.query().build().find();
       // mUserAdapter.setNewData(mUserList);
       // rvUser.smoothScrollToPosition(mUserList.size() - 1);

        for (int i = 0; i < mList.size(); i++) {
            LogUtils.e("TAG", TAG + "---queryAll---" + mList.get(i).getUserId() + "---" + mList.get(i).getUserName());
        }

        updateAllData();
    }

    private void updateAllData(){
        listAdapter.notifyDataSetChanged();
    }


    private void initData() {

        //ID生成器
        mIdWorker = new SnowflakeIdGenerator(0, 0);

        mBox.removeAll();

        mList = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            SmallTargetBean user = new SmallTargetBean();
            user.setUserId(String.valueOf(mIdWorker.nextId()));
            // 随机生成汉语名称
            user.setUserName(NameUtils.createRandomZHName(random.nextInt(4) + 1));
            mList.add(user);
        }

        listAdapter = new SmallTargetListAdapter(mActivity, mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(listAdapter);

        mBox.put(mList);
    }


    /**
     * 显示今日星期
     */
    private void showTodayWeek() {
        String week = TimeUtil.dateToWeek(System.currentTimeMillis() + "");
        tvTuesday = mainView.findViewById(R.id.tv_tuesday);

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