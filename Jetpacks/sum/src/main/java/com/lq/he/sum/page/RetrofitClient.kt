package com.lq.he.sum.page

import retrofit2.Retrofit

class RetrofitClient private constructor() {

    lateinit var retrofit: Retrofit

    fun RetrofitClient() {
        retrofit = Retrofit.Builder()
            .baseUrl("")
//            .addConverterFactory()
//            .client()
            .build()
    }

    companion object {
        val BASE_URL = ""
        val APP_KEY = ""

        /**
         * 单例模式
         * https://www.jianshu.com/p/5797b3d0ebd0
         */
        // 线程安全，双重锁
        val instance: RetrofitClient by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }
        // 线程安全的懒汉式
        private val instances: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }

        @Synchronized
        fun get(): RetrofitClient {
            return instances!!
        }

        // 懒汉式, 这里不用getInstance作为为方法名，是因为在伴生对象声明时，内部已有getInstance方法
        fun get2(): RetrofitClient {
            return instances!!
        }
    }
}