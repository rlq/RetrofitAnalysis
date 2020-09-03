package com.lq.he.sum;

import android.app.Application;

import com.lq.he.sum.utils.ApplicationUtils;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtils.init(this);
        String processName = ApplicationUtils.getCurrentProcessName(this);
        String processNameSuffix = ApplicationUtils.getCurrentProcessNameSuffix(processName);

        if (!ApplicationUtils.isMainProcess(this)) {
            // 非主进程
            return;
        }
    }
}
