package com.help.tips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.MapView;
import com.freeds.toolutil.LogUtils;


/**
 * @author Administrator
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private MapView mMapView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mMapView = (MapView) view.findViewById(R.id.bmapView);

        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText("地图");

        view.findViewById(R.id.iv_back_logo).setVisibility(View.GONE);


        LogUtils.e(TAG + "---onCreateView---");

        BaiduMapOptions options = new BaiduMapOptions();
        options.mapType(BaiduMap.MAP_TYPE_SATELLITE);
        ViewGroup viewGroup = (ViewGroup) view;
        MapView mapView = new MapView(getActivity(), options);
        ((ViewGroup) view).addView(mapView);

        return view;
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
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
}