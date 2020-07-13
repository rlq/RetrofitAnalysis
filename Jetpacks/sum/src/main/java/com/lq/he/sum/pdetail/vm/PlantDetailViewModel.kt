package com.lq.he.sum.pdetail.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lq.he.sum.data.GardenPlantingRepository
import com.lq.he.sum.data.PlantRepository
import kotlinx.coroutines.launch

// 某个plantId植物详情VM类
class PlantDetailViewModel internal constructor(
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModel() {

    // 通过plantId找到对应的属性
    val isPlanted = gardenPlantingRepository.isPlanted(plantId)
    val plant = plantRepository.getPlant(plantId)

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }

}