package com.help.tips.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BaseDialog extends Dialog {

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setProperty() {
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        // 设置对话框的透明度,1f不透明
        wl.alpha = 1f;
        wl.gravity = Gravity.CENTER;//设置显示在中间
        window.setAttributes(wl);
    }

    public void setProperty(int x, int w, int h) {
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        //设置对话框的位置．0为中间
        wl.x = x;
        wl.width = w;
        wl.height = h;
        wl.alpha = 1f;
        wl.gravity = Gravity.CENTER;//设置显示在中间
        window.setAttributes(wl);
    }

    public void setProperty(int x, int y, int w, int h) {
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = x;
        wl.y = y;
        wl.width = w;
        wl.height = h;
        wl.alpha = 1f;
        wl.gravity = Gravity.CENTER;//设置显示在中间
        window.setAttributes(wl);
    }


    public void setProperty(int x, int y, int w, int h, int position) {
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = x;
        wl.y = y;
        wl.width = w;
        wl.height = h;
        wl.alpha = 1f;
        wl.gravity = position;//设置显示在中间
        window.setAttributes(wl);
    }
}
