package com.lq.he.sum.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.lq.he.sum.data.AppDatabase
import com.lq.he.sum.data.PLANT_DATA_FILENAME
import com.lq.he.sum.data.Plant
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {
    // 实现doWork
    override suspend fun doWork(): Result = coroutineScope {
        try {
            // 这里就是子线程读取文件，然后插入DB
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                // JsonReader 是com.google.gson.stream.JsonReader
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Plant>>() {}.type
                    val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)
                    val database = AppDatabase.getInstance(applicationContext)
                    database.plantDao().insertAll(plantList)
                    Log.e(TAG, "jsonReader :" + plantList.toString())
                    Result.success()

                }
            }
        } catch (ex: Exception) {
            // 有个异常，没加PLANT_DATA_FILENAME json文件
            Log.e(TAG, "jsonReader222 :", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}