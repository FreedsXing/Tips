package com.help.tips.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.freeds.tool.LogUtils;
import com.help.tips.R;
import com.help.tips.base.BaseFragment;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;


public class WriteFragment extends BaseFragment {


    String mUrl = "https://mp.weixin.qq.com/s?__biz=MzI3NjU1NTMwNQ==&mid=2247484491&idx=1&sn=f5a510ad2a11ab3357db085edc52f2ee&chksm=eb72f70ddc057e1bd6aecf74879d42390c5f920a7a3637eb2ca0e6fe55733a6784611b94c168&mpshare=1&scene=24&srcid=0914QYxBFxUvnXj1ElmShSuY&sharer_sharetime=1631588435460&sharer_shareid=aa0a4feda6a2fd4ad8b4b330e974dded&ascene=4&devicetype=android-30&version=28000a3d&abtest_cookie=AAACAA%3D%3D&lang=zh_CN&exportkey=AUtUu5Dd5ZwGwjQmdt7sHFc%3D&pass_ticket=%2B2snBUqA18JXgVnQjOW9%2FRn6D1lyUqE1p%2FRINFJ6uO%2B5n%2BsIW9%2BEX4iQD8Lmzq39&wx_header=1";


    @BindView(R.id.tv)
    TextView tvTitle;
    @BindView(R.id.web)
    public WebView mWebView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1: {
                    mWebView.goBack();
                }
                break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        mainView = inflater.inflate(R.layout.fragment_write, container, false);
        mUnbinder = ButterKnife.bind(this, mainView);


        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(mUrl);

        tvTitle.setText("新手养宠记");

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("TAG" + "---shouldOverrideUrlLoading---" + url);
                //view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
                    if (event.getAction() == KeyEvent.ACTION_DOWN) { //只处理一次
                        handler.sendEmptyMessage(1);
                    }
                    return true;
                }
                return false;
            }
        });

        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mBoxStore.close();
    }

}