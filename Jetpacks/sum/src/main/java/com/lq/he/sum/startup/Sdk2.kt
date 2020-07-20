package com.lq.he.sum.startup

import android.content.Context
import android.util.Log

class Sdk2 {

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
            Log.e(TAG, sApplicationContext.getPackageName())
        }
    }
}