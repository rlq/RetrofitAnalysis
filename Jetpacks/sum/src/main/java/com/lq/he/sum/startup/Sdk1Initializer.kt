package com.lq.he.sum.startup

import android.content.Context
import java.util.*

class Sdk1Initializer : Initializer<Sdk1> {

    override fun create(context: Context?): Sdk1 {
        Sdk1.init(context)
        return Sdk1.getInstance()!!
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?>? {
        return Collections.emptyList()
    }
}