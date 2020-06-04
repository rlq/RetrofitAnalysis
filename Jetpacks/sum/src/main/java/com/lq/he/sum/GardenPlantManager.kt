package com.lq.he.sum

import android.content.Context
import com.lq.he.sum.data.AppDatabase
import com.lq.he.sum.data.GardenPlantingRepository
import com.lq.he.sum.viewmodels.GardenPlantingListViewModelFactory

// static类
object GardenPlantManager {

    fun provideGardenPlantingListVieModelFactory(context: Context): GardenPlantingListViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(repository)
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).gardenPlantingDao()
        )
    }

}