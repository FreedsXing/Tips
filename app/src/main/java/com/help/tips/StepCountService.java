package com.help.tips;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.freeds.tool.LogUtils;

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
//        Intent intent = new Intent(this, SetPlanActivity.class);
//        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
//        Notification notification = new NotificationCompat.Builder(this)
//                .setContentTitle("title")
//                .setContentText("context")
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo))
//                .setContentIntent(pi)
//                .build();
//        startForeground(1, notification);
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
