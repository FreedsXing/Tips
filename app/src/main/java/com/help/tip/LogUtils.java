package com.help.tip;

import android.util.Log;

/**
 * Created by Song on 2018/11/5.
 */

public class LogUtils {

    public final static String post_request = "post_request";
    public final static String post_response = "post_response";

    /**
     * 是否打印 LOG
     */
    public final static boolean FLAG = true;

    /*单独打印生命周期调用过程*/
    public final static boolean FLAG_LFIE_CYCLE = true;

    /*单独接口请求调用过程*/
    public final static boolean flag_request_response = true;

    public static boolean isLoging = true;


    /**
     * 单独打印生命周期的调用
     * @param msg
     */
    public static void infoLifeCycle(String msg){
        if (FLAG_LFIE_CYCLE){
            Log.i("TAG", msg);
        }
    }

    /**
     * 打印接口数据
     * @param tag
     * @param msg
     */
    public static void infoRequestResponseMess(String tag, String path, String methed, String msg){
        if (flag_request_response){
            Log.e("TAG", tag + "---net-message---" + path + "---" + msg);
        }
    }

    public static void e(String tag, String msg) {
        if (FLAG) {
            Log.e(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (FLAG) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (FLAG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (FLAG) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (FLAG) {
            Log.w(tag, msg);
        }
    }

    public static void logLong(String msg) {
        if (FLAG) {
//           LogUtils.e("-----test----",msg);
            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                LogUtils.e("-----test----", msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    LogUtils.e("-----test----", logContent);
                }
                LogUtils.e("-----test----", msg);// 打印剩余日志
            }
        }
    }


    public static void logMes(String msg) {
        if (isLoging) {
//           LogUtils.e("-----test----",msg);
            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                LogUtils.e("-----test----", msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    LogUtils.e("-----test----", logContent);
                }
                LogUtils.e("-----test----", msg);// 打印剩余日志
            }
        }
    }
}
