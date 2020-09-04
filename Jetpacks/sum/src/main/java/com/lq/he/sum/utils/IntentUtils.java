package com.lq.he.sum.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.annotation.NonNull;

public class IntentUtils {

    public static boolean canStartActivity(@NonNull Context context, @NonNull Intent intent) {
        return context.getPackageManager().resolveActivity(intent,
                PackageManager.MATCH_DEFAULT_ONLY) != null;
    }

    public static boolean startActivity(@NonNull Context context, @NonNull Intent intent) {
        if (canStartActivity(context, intent)) {
            context.startActivity(intent);
            return true;
        }
        return false;
    }
}
