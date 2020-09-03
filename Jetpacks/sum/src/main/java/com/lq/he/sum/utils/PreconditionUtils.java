package com.lq.he.sum.utils;

import android.os.Looper;

public class PreconditionUtils {

    public static boolean checkMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
