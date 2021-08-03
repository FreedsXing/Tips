package com.help.tips.second.step;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.freeds.toolutil.LogUtils;

/**
 * @author Administrator
 */
public class StepCountService extends Service {

    private static final String TAG = "StepCountService";

    public StepCountService() {
    }


    private ModelBinder modelBinder = new ModelBinder();
    public class ModelBinder extends Binder {
        public void start(){
            LogUtils.e(TAG + "---start---");
        }
        public void end(){
            LogUtils.e(TAG + "---end---");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
        return modelBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.e("TAG", TAG + "---onCreate---");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.e("TAG", TAG + "---onStartCommand---");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.e("TAG", TAG + "---onDestroy---");
    }
}
