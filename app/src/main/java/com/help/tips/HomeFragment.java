package com.help.tips;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorInfo;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.freeds.toolutil.LogUtils;
import com.help.tips.util.ToastUtils;

import java.util.List;


/**
 * @author Administrator
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "HomeFragment";

    private MapView mMapView = null;

    private View mainView;
    private TextView tvNomal;
    private TextView tvSatellite;
    private TextView tvBlank;

    private EditText etCity;
    private EditText etPlace;

    BaiduMapOptions options;
    PoiSearch poiSearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_home, container, false);
        //mMapView = (MapView) view.findViewById(R.id.bmapView);

        TextView tvTitle = mainView.findViewById(R.id.tv_title);
        tvTitle.setText("地图");

        tvNomal = mainView.findViewById(R.id.tv_normal);
        tvSatellite = mainView.findViewById(R.id.tv_satellite);
        tvBlank = mainView.findViewById(R.id.tv_blank);


        etCity = mainView.findViewById(R.id.et_city);
        etPlace = mainView.findViewById(R.id.et_place);

        mainView.findViewById(R.id.iv_back_logo).setVisibility(View.GONE);


        LogUtils.e(TAG + "---onCreateView---");


        options = new BaiduMapOptions();
        options.mapType(BaiduMap.MAP_TYPE_SATELLITE);
        options.compassEnabled(true);

        mMapView = new MapView(getActivity(), options);
        ((ViewGroup) mainView).addView(mMapView);


        //开启交通图
        mMapView.getMap().setTrafficEnabled(true);


        tvNomal.setOnClickListener(this);
        tvSatellite.setOnClickListener(this);
        tvBlank.setOnClickListener(this);

        poiSearch = PoiSearch.newInstance();

        mainView.findViewById(R.id.tv_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.zfxf.douniu");
//                getActivity().startActivity(intent);

//                LivingActivity
//                ComponentName componentName = new ComponentName("com.zfxf.douniu", "com.zfxf.douniu.activity.liveshow.LivingActivity");
//                getActivity().startActivity(new Intent().setComponent(componentName));
                Intent intent = new Intent();
                intent.setAction("login");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
            }
        });


        etPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                LogUtils.e(TAG + "---" + etCity.getText().toString());
                LogUtils.e(TAG + "---" + etPlace.getText().toString());

                if (!TextUtils.isEmpty(etCity.getText()) && !TextUtils.isEmpty(etPlace.getText())){
                    poiSearch.searchInCity(new PoiCitySearchOption()
                            .city(etCity.getText().toString())
                            .keyword(etPlace.getText().toString())
                            .pageNum(0));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult result) {
                List<PoiInfo> list = result.getAllPoi();
                for (int i = 0; i < list.size(); i++) {
                    PoiInfo info = list.get(i);
                    LogUtils.e("TAG" + "----" + info.address);
                    //ToastUtils.toastMessage(info.address);=====有bug
                    Toast.makeText(getActivity(), info.address, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult result) {
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult result) {
                LogUtils.e("TAG" + "---onGetPoiDetailResult---");

                List<PoiDetailInfo> list = result.getPoiDetailInfoList();
                for (int i = 0; i < list.size(); i++) {
                    PoiDetailInfo info = list.get(i);
                 LogUtils.e("TAG" + "----" + info.getArea() + "---" + info.getDetailUrl() + "---" + info.getAddress() + "---" + info.getName());
                }

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult result) {
                LogUtils.e("TAG" + "---onGetPoiIndoorResult---");

                List<PoiIndoorInfo> list = result.getArrayPoiInfo();
                for (int i = 0; i < list.size(); i++) {
                    PoiIndoorInfo info = list.get(i);
                    LogUtils.e("TAG" + "----" + info.address);
                }

            }
        };
        poiSearch.setOnGetPoiSearchResultListener(listener);


        return mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放检索实例
        poiSearch.destroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_normal:
                options.mapType(BaiduMap.MAP_TYPE_NORMAL);
                ((ViewGroup) mainView).removeView(mMapView);
                mMapView = new MapView(getActivity(), options);
                ((ViewGroup) mainView).addView(mMapView);
                break;
            case R.id.tv_satellite:
                options.mapType(BaiduMap.MAP_TYPE_SATELLITE);
                ((ViewGroup) mainView).removeView(mMapView);
                mMapView = new MapView(getActivity(), options);
                ((ViewGroup) mainView).addView(mMapView);
                break;
            case R.id.tv_blank:
                options.mapType(BaiduMap.MAP_TYPE_NONE);
                ((ViewGroup) mainView).removeView(mMapView);
                mMapView = new MapView(getActivity(), options);
                ((ViewGroup) mainView).addView(mMapView);
                break;
            default:
                break;
        }
    }
}