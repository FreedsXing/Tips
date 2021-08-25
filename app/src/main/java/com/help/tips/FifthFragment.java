package com.help.tips;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.freeds.toolutil.AppInforUtil;
import com.freeds.toolutil.LogUtils;
import com.help.tips.activity.AboutUsActivity;
import com.help.tips.activity.LoginActivity;
import com.help.tips.base.BaseFragment;
import com.help.tips.util.CacheDataManager;
import com.help.tips.util.SharedPreferencesUtil;

import java.io.File;


public class FifthFragment extends BaseFragment implements View.OnClickListener {

    private View mainView;

    private LinearLayout llTextSize;
    private LinearLayout llClearCache;
    private RelativeLayout llAppVersion;
    private LinearLayout llAboutUs;
    private LinearLayout llLogout;

    private TextView tvCache;
    private TextView tvAppVersion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_fifth, container, false);

        TextView tvTitle = mainView.findViewById(R.id.tv_title);
        tvTitle.setText("我");
        mainView.findViewById(R.id.iv_back_logo).setVisibility(View.GONE);

        llTextSize = mainView.findViewById(R.id.ll_textSise);
        llClearCache = mainView.findViewById(R.id.ll_clear_cache);
        llAppVersion = mainView.findViewById(R.id.ll_version);
        llAboutUs = mainView.findViewById(R.id.ll_about_us);
        llLogout = mainView.findViewById(R.id.ll_login_out);

        tvCache = mainView.findViewById(R.id.tv_cache);
        tvAppVersion = mainView.findViewById(R.id.tv_app_version);

        llLogout.setOnClickListener(this);
        llAboutUs.setOnClickListener(this);
        llAppVersion.setOnClickListener(this::onClick);
        llClearCache.setOnClickListener(this::onClick);
        llTextSize.setOnClickListener(this::onClick);



        String appVersion = AppInforUtil.getAppVersionName(getActivity());
        tvAppVersion.setText(appVersion);

        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_textSise:
                Configuration configuration = getResources().getConfiguration();
                LogUtils.e("TAG" + "---onClick---" + configuration.fontScale);
                configuration.fontScale = 1.25f;
                break;
            case R.id.ll_clear_cache:
                CacheDataManager.clearAllCache(getActivity());
                tvCache.setText("0.00M");
                try {
                    LogUtils.e("TAG" + "---onClick---" + getTotalCacheSize(getActivity()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ll_version:
                Toast.makeText(getActivity(), "当前已是最新版本", Toast.LENGTH_LONG).show();
                break;
            case R.id.ll_about_us:
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                intent.putExtra("key", "gggo");
                startActivity(intent);
                break;
            case R.id.ll_login_out:
                SharedPreferencesUtil.putBoolean(getActivity(), "login", false);
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    /**
     * 获取整体缓存大小
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        LogUtils.e("TAG" + "---getTotalCacheSize---" + cacheSize);
        return getFormatSize(cacheSize);
    }


    /**
     * 格式化单位
     * @param size
     */
    public static String getFormatSize(long size) {
        long kb = size / 1024;
        int m = (int) (kb / 1024);
        int kbs = (int) (kb % 1024);
        return m + "." + kbs + "M";
    }


    /**
     * 获取文件
     * Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
     * Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

}