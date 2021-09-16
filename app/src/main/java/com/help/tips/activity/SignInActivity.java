package com.help.tips.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeds.tool.LogUtils;
import com.help.tips.R;
import com.help.tips.base.BaseActivity;
import com.help.tips.bean.SmallTargetBean;

import org.litepal.LitePal;

import butterknife.BindView;

public class SignInActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SignInActivity";

    @BindView(R.id.iv_back)
    public ImageView ivBack;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_btn)
    public TextView tvBtn;
    @BindView(R.id.tv_btn2)
    public TextView tvBtn2;
    @BindView(R.id.tv_test)
    public TextView tvTest;

    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        LogUtils.e(getClass().getSimpleName());


        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("name"));

        mId = intent.getIntExtra("id", 00);


        SmallTargetBean bean = LitePal.find(SmallTargetBean.class, mId);

        LogUtils.e( TAG + "---LitePal---" + bean.getId() + "---" + bean.getTargetName());
        boolean isLike = bean.isLike();
        if (isLike){
            tvTest.setText("取消打卡");
        }else {
            tvTest.setText("添加打卡");
        }


        ivBack.setOnClickListener(this);
        tvBtn.setOnClickListener(this);
        tvBtn2.setOnClickListener(this);

        tvTest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_btn:
                LogUtils.e("TAG", "---onClick---");
                Animation translateAnit = new TranslateAnimation(0, 0, 0, -300);
                translateAnit.setDuration(1200);
                translateAnit.setFillAfter(true);
                tvBtn.setAnimation(translateAnit);
                tvBtn.startAnimation(translateAnit);
                tvBtn.setClickable(false);


                SmallTargetBean targetBean = LitePal.find(SmallTargetBean.class, mId);
                int finished = targetBean.getFinishDay();
                targetBean.setFinishDay(++finished);
                targetBean.save();
                break;

            case R.id.tv_test:
                SmallTargetBean targetBean2 = LitePal.find(SmallTargetBean.class, mId);
                boolean isLike = targetBean2.isLike();
                if (isLike){
                    tvTest.setText("添加打卡");
                    targetBean2.setLike(false);
                }else {
                    tvTest.setText("取消打卡");
                    targetBean2.setLike(true);
                }
                targetBean2.save();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}