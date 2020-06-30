package com.lq.he.sum

import android.content.Context
import androidx.fragment.app.Fragment
import com.lq.he.sum.data.AppDatabase
import com.lq.he.sum.data.GardenPlantingRepository
import com.lq.he.sum.data.PlantRepository
import com.lq.he.sum.gplant.vm.GardenPlantingListViewModelFactory
import com.lq.he.sum.pdetail.vm.PlantDetailViewModel
import com.lq.he.sum.pdetail.vm.PlantDetailViewModelFactory
import com.lq.he.sum.plist.vm.PlantListViewModelFactory

// static类
object GardenPlantManager {

    // 获取到植物目录的工厂
    fun providePlantListViewModelFactory(fragment: Fragment): PlantListViewModelFactory {
        val repository = getPlantRepository(fragment.requireContext())
        return PlantListViewModelFactory(
            repository,
            fragment
        )
    }

    // 获取植物目录的Dao
    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context.applicationContext).plantDao())
    }

    // 获取我的花园 种植的植物 工厂
    fun provideGardenPlantingListVieModelFactory(context: Context): GardenPlantingListViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(
            repository
        )
    }

    // 获取种植的花园植物 Dao
    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).gardenPlantingDao()
        )
    }

    // 获取植物详情的VM工厂
    fun providePlantDetailViewModelFactory(context: Context, plantId: String): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(context),
            getGardenPlantingRepository(context), plantId)
    }



}