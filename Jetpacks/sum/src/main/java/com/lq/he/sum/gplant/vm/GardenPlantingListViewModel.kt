package com.lq.he.sum.gplant.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lq.he.sum.data.GardenPlantingRepository
import com.lq.he.sum.data.PlantAndGardenPlantings

// 种植植物列表 viewModel 类
class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {
    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
        gardenPlantingRepository.getPlantedGardens()
}