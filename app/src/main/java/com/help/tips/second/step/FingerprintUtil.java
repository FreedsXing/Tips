package com.help.tips.second.step;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Freeds on 2021/7/22 0022.
 */
public class FingerprintUtil {
    private static final String ACTION_SETTING = "android.settings.SETTINGS";

    public static void openFingerPrintSettingPage(Context context) {
        Intent intent = new Intent(ACTION_SETTING);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
        }
    }
}
