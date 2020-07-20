package com.lq.he.sum.startup

import android.content.Context
import android.util.Log

/**
 * sdk1对外提供Sdk1类，包含初始化方法init(Context)，
 * 实例获取方法getInstance()和对外的服务方法printApplicationName()。
 * 为了使用App Startup，需要提供一个初始化器如下：
 */
class Sdk1 {

    companion object {
        private val TAG = "Sdk1"
        private var sApplicationContext: Context? = null
        @Volatile
        private var sInstance: Sdk1? = null

        fun init(applicationContext: Context?) {
            sApplicationContext = applicationContext
            Log.e(TAG, "Sdk1 is initialized")
        }


        fun getInstance(): Sdk1? {
            if (sInstance == null) {
                synchronized(Sdk1::class.java) {
                    if (sInstance == null) {
                        sInstance = Sdk1()
                    }
                }
            }
            return sInstance
        }

        fun printApplicationName() {
            Log.e(TAG, sApplicationContext?.packageName)
        }
    }
}