package com.help.tips.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeds.toolutil.LogUtils;
import com.help.tips.R;

public class SignInActivity extends BaseActivity implements View.OnClickListener {


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