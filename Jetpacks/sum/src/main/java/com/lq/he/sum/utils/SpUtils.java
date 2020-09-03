package com.lq.he.sum.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    private static final String PREFERENCES_NAME = "com_device_sp";
    private static final String KEY_DEVICE = "device_id";

    public static String getDevicesId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_DEVICE, "");
    }

    public static void setDevicesId(Context context, String deviceId) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, context.MODE_PRIVATE);
        preferences.edit().putString(KEY_DEVICE, deviceId).apply();
    }
}
