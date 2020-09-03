package com.lq.he.sum.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ApplicationUtils {

    @SuppressLint("StaticFieldLeak")
    private static Application sApp;
    private static Handler sHandler;

    private static String sProcessName;

    public static void init(Application application) {
        sApp = application;
        sHandler = new Handler();
    }

    @Nullable
    public static Application getApplication() {
        return sApp;
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isMainProcess(@NonNull Context context) {
        return isMainProcess(getCurrentProcessName(context));
    }

    public static boolean isMainProcess(@NonNull String processName) {
        return TextUtils.isEmpty(getCurrentProcessNameSuffix(processName));
    }

    /**
     * 获取当前进程的后缀
     */
    public static String getCurrentProcessNameSuffix(@NonNull String processName) {
        String suffix = "";
        int index = processName.lastIndexOf(":");
        if (index > 0 && index + 1 < processName.length()) {
            suffix = processName.substring(index + 1);
        }
        return suffix;
    }

    public static String getCurrentProcessName(@NonNull Context context) {
        if (TextUtils.isEmpty(sProcessName)) {
            return sProcessName;
        }

        String processName = context.getPackageName();
        int pid = Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                processName = processInfo.processName;
                break;
            }
        }
        sProcessName = processName;
        return processName;
    }
}
