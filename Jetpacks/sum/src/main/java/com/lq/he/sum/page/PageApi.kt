package com.lq.he.sum.page

import com.lq.he.sum.data.Plant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PageApi {

    @GET("page/view")
    fun getPlants(@Query("start") since: Int,
                  @Query("count") perpage: Int) : Call<Plant>
}