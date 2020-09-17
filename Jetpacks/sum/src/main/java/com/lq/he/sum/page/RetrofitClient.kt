package com.lq.he.sum.page

import com.lq.he.sum.data.Plant
import okhttp3.OkHttpClient
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    lateinit var retrofit: Retrofit

    fun RetrofitClient() {
        val client = OkHttpClient.Builder()
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create()) // 添加用于解析数据的转换库
            .client(client)
            .build()
    }

    fun getApi() : PageApi {
        return retrofit.create(PageApi::class.java)
    }

    fun getPlants() {

    }

    fun enqueue(response : Callback<Plant>) {

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