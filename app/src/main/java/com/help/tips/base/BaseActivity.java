package com.help.tips.base;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.freeds.toolutil.LogUtils;
import com.help.tips.util.ActivityCollector;

public class BaseActivity extends AppCompatActivity {


    //全局绑定and解绑
    private Unbinder mUnbinder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.e("---" + getClass().getSimpleName() + "---onCreate");
        ActivityCollector.addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        // 也可以调换 Activity的setContentView 和 super.onCreate(savedInstanceState) 的位置
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        LogUtils.e("---" + getClass().getSimpleName() + "---onDestroy");
        ActivityCollector.removeActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e("---" + getClass().getSimpleName() + "---onStart" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.e("---" + getClass().getSimpleName() + "---onStop");
    }


    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.e("---" + getClass().getSimpleName() + "---onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("---" + getClass().getSimpleName() + "---onResume");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.e("---" + getClass().getSimpleName() + "---onRestart" );
    }




    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtils.e("---" + getClass().getSimpleName() + "---onNewIntent" );
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    @Override
    public void setTheme(int resId) {
        super.setTheme(resId);
    }


    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    @Override
    public Resources.Theme getTheme() {
        return super.getTheme();
    }
}