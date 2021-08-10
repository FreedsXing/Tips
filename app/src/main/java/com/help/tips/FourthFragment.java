package com.help.tips;

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

import androidx.fragment.app.Fragment;

import com.freeds.toolutil.LogUtils;

public class FourthFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "FourthFragment";

    private static final String ACTIVITY_PAGE = "https://huodong.weibo.cn/olympics2021/h5_medal?sinainternalbrowser=topnav&portrait_only=1&share_menu=1&disable_sinaurl=1&disable_gesture_back=1&topnavstyle=1";

    private WebView webView;
    private View mainView;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1: {
                    webView.goBack();
                }
                break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_fourth, container, false);
        webView = mainView.findViewById(R.id.web);

        TextView tvTitle = mainView.findViewById(R.id.tv_title);
        tvTitle.setText("搜索");
        mainView.findViewById(R.id.iv_back_logo).setVisibility(View.GONE);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(ACTIVITY_PAGE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.e("TAG" + "---shouldOverrideUrlLoading---" + url);
                //view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
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
    public void onClick(View v) {

    }
}