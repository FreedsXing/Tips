package com.help.tips.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeds.toolutil.LogUtils;
import com.help.tips.R;
import com.help.tips.bean.SmallTargetBean;

import org.litepal.LitePal;

import java.util.List;

public class SignInActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SignInActivity";

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvBtn;
    private TextView tvBtn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        LogUtils.e(getClass().getSimpleName());

        ivBack = findViewById(R.id.iv_back);
        tvTitle = findViewById(R.id.tv_title);

        tvBtn = findViewById(R.id.tv_btn);
        tvBtn2 = findViewById(R.id.tv_btn2);

        List<SmallTargetBean> list = LitePal.findAll(SmallTargetBean.class);
        for (int i = 0;  i< list.size(); i++) {
            SmallTargetBean bean = list.get(i);
            LogUtils.e( TAG + "---LitePal---" + bean.getId() + "---" + bean.getUserName());
        }


        tvTitle.setText(getIntent().getStringExtra("name"));

        ivBack.setOnClickListener(this);
        tvBtn.setOnClickListener(this);
        tvBtn2.setOnClickListener(this);
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