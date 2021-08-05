package com.help.tips.util;

import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by Freeds on 2021/6/21 0021.
 */
public class
ToastUtils {

    private static Toast toast;


    /**
     * Toast提示
     * @param msg
     */
    public static void toastMessage(String msg) {
        if (TextUtils.isEmpty(msg)  || "null".equals(msg) || msg.contains("null")) {
            return;
        }
        int duration;
        if (msg.length() > 15) {
            duration = Toast.LENGTH_LONG;
        } else {
            duration = Toast.LENGTH_SHORT;
        }
        if (toast == null) {
            toast = Toast.makeText(AppContext.getAppContext(), msg, duration);
        } else {
            toast.setDuration(duration);
            toast.setText(msg);
        }

        toast.show();
    }
}
