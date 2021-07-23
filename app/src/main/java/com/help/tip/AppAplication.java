package com.help.tip;

import android.app.Application;
import android.content.Context;

/**
 * Created by Freeds on 2021/7/23 0023.
 */
public class AppAplication extends Application {

    protected static Context mContext;

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getAppContext();
    }
}
