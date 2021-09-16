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

import com.freeds.tool.LogUtils;
import com.help.tips.R;
import com.help.tips.base.BaseFragment;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;


public class XitujuejinFragment extends BaseFragment {


    String mUrl = "https://juejin.cn/";

    private WebView mWebView;

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
        mainView = inflater.inflate(R.layout.fragment_test4, container, false);
        mUnbinder = ButterKnife.bind(this, mainView);


        mWebView = mainView.findViewById(R.id.web);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.loadUrl(mUrl);

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