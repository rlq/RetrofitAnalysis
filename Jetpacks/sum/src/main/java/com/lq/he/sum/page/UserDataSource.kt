package com.lq.he.sum.page

import androidx.paging.PageKeyedDataSource
import com.lq.he.sum.data.Plant
import okhttp3.OkHttpClient

class UserDataSource : PageKeyedDataSource<Int, Plant>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Plant>
    ) {
        // 页面首次加载数据时会调用这个方法。加载第一页的数据，加载成功后通过callback.onResult将数据返回给PageList


        RetrofitClient.get()
            .getApi()
            .getPlants()

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Plant>) {
        // 加载下一页的工作在该方法内进行。
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Plant>) {
        // 暂时用不到，什么都不用做
    }

    companion object {
        val FIRST_PAGE = 1
        val PER_PAGE = 8;
        val SITE = "stackoverflow"

    }
}