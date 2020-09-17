package com.lq.he.sum.page

import com.lq.he.sum.data.Plant
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PageApi {

    @GET("page/view")
    fun getPlants(@Query("start") since: Int,
                  @Query("count") perpage: Int) : Call<Plant>

    @POST("user/login")
    fun login(@Body body: RequestBody) : Call<Response<Plant>>
}