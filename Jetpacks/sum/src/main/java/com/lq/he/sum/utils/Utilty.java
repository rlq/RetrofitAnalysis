package com.lq.he.sum.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;

/**
 * A wrapper friendly for mock.
 */
@SuppressLint("HardwareIds")
public class Utilty {
    public static String getModel() {
        return Build.MODEL;
    }

    public static String getOldSerial() {
        return Build.SERIAL;
    }

    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getSerial() {
        if (VERSION.SDK_INT >= VERSION_CODES.O) {
            return Build.getSerial();
        } else {
            //noinspection deprecation
            return Build.SERIAL;
        }
    }

    public static int getSdkInt() {
        return VERSION.SDK_INT;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getWifiAddress(Context cxt) {
        WifiManager wifiMgr = (WifiManager) cxt.getApplicationContext()
                .getSystemService(Context.WIFI_SERVICE);
        return wifiMgr.getConnectionInfo().getMacAddress();
    }

    @RequiresPermission("android.permission.BLUETOOTH")
    @Nullable
    public static String getBtAddress() {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            return null;
        }
        return btAdapter.getAddress();
    }

    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    public static String getAndroidDeviceId(Context cxt) {
        TelephonyManager telMgr = (TelephonyManager) cxt.getSystemService(Context.TELEPHONY_SERVICE);
        return telMgr != null ? telMgr.getDeviceId() : "";
    }

    public static String getAndroidId(Context cxt) {
        return Settings.Secure.getString(cxt.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
