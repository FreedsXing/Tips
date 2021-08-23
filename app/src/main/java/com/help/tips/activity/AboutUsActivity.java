package com.help.tips.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.freeds.toolutil.LogUtils;
import com.help.tips.R;
import com.help.tips.base.BaseActivity;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "AboutUsActivity";

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        LogUtils.e(getClass().getSimpleName());

        ivBack = findViewById(R.id.iv_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}