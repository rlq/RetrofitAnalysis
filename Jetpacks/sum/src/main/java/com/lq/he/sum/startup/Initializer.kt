package com.lq.he.sum.startup

import android.content.Context
import androidx.annotation.NonNull

// https://mp.weixin.qq.com/s/bDwsFUOSTUiHcA0lxgIZaA
interface Initializer<T> {

    @NonNull
    fun create(context : Context?) : T

    // List<Class<? extends Initializer<?>>> dependencies();
    @NonNull
    fun dependencies(): List<Class<out Initializer<*>?>?>?
}