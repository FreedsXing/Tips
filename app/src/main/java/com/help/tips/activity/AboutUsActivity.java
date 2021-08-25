package com.help.tips.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.freeds.toolutil.LogUtils;
import com.help.tips.R;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "AboutUsActivity";

    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        LogUtils.e("TAG", TAG + "----onCreate" + getIntent().getStringExtra("key"));


        LogUtils.e(getClass().getSimpleName());

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                //finish();
                Intent intent = new Intent(AboutUsActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        LogUtils.e("TAG", TAG + "----onNewIntent" + intent.getStringExtra("key"));
        super.onNewIntent(intent);
    }
}