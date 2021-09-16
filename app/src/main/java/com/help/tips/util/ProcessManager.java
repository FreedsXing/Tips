package com.help.tips.util;

public class ProcessManager {

    /**
     * 杀掉当前进程
     */
    public static void kllProcess(){

        //获取当前进程id
        int idCurrentProcess = android.os.Process.myPid();

        //该方法只能用于杀掉当前进程
        android.os.Process.killProcess(idCurrentProcess);
    }
}
