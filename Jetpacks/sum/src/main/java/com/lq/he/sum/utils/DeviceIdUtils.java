package com.lq.he.sum.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import androidx.annotation.RequiresPermission;
import androidx.annotation.VisibleForTesting;

import static com.lq.he.sum.utils.DeviceIdUtils.DeviceIdVersion.v1;

@SuppressLint("HardwareIds")
public class DeviceIdUtils {

    public @interface DeviceIdVersion {
        int v1 = 1;
        int v2 = 2;
    }

    @VisibleForTesting
    private static final String ILLEGAL_BLUETOOTH_ADDRESS = "00:00:00:00:00:00";
    @VisibleForTesting
    private static final String DEFAULT_BLUETOOTH_ADDRESS = "00:00:46:66:30:01";
    @VisibleForTesting
    private static final String ANDROID_M_FAKE_ADDRESS = "02:00:00:00:00:00";

    // 无效的deviceId
    private static final String ILLEGAL_DEVICE_ID = "illegal_device_id";

    private static int sDeviceIdVersion = v1;
    private static String sDeviceId;
    @VisibleForTesting
    private static String sBtAddress;

    public static void initForPhone(@DeviceIdVersion int version) {
        sDeviceIdVersion = version;
    }

    @SuppressLint("MissingPermission")
    public static String getPhoneDeviceId(Context context) {
        if (!TextUtils.isEmpty(sDeviceId)) {
            return sDeviceId;
        }

        synchronized (DeviceIdUtils.class) {
            String deviceId = SpUtils.getDevicesId(context);
            if (TextUtils.isEmpty(deviceId)) {
                if (sDeviceIdVersion == v1) {
                    deviceId = generatePhoneDeviceIdV2(context);
                } else {
                    deviceId = generatePhoneDeviceId(context);
                }
                if (deviceId.equals(ILLEGAL_DEVICE_ID)) {
                    return ILLEGAL_DEVICE_ID;
                } else {
                    SpUtils.setDevicesId(context, deviceId);
                }
            }
            sDeviceId = deviceId;
        }
        return sDeviceId;
    }

    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    private static String generatePhoneDeviceIdV2(Context context) {
        String model = Build.MODEL;
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        String data = model + "#" + androidId;
        String serial = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL; // 已废弃
        data += serial;
        String deviceId = "";
        try {
            deviceId = DigestEncodingUtils.sha1(data).substring(0, 32);
        } catch (Exception e) {
            return ILLEGAL_DEVICE_ID;
        }
        return deviceId;
    }

    @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
    private static String generatePhoneDeviceId(Context context) {
        String deviceId = "";
        try {
            StringBuilder sb = new StringBuilder();
            String serial = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? Build.getSerial() : Build.SERIAL; // 已废弃
            String wifiAddress = Utilty.getWifiAddress(context);
            // 默认的wifi地址，与当前wifi
            if (ANDROID_M_FAKE_ADDRESS.equals(wifiAddress)) {
                sb.append(Build.MODEL).append(serial);
            } else {
                sb.append(Utilty.getAndroidId(context));
            }
            deviceId = DigestEncodingUtils.sha1(sb.toString()).substring(0, 32);
        } catch (Exception e) {
            return ILLEGAL_DEVICE_ID;
        }
        return deviceId;
    }


}
