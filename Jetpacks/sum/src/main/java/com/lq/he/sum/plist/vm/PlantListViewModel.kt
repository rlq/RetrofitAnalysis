package com.lq.he.sum.plist.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.lq.he.sum.data.Plant
import com.lq.he.sum.data.PlantRepository

// 植物目录
// plantList VM与LiveData结合起来获取数据提供给View使用
// 使用的是plant List 数据结构
class PlantListViewModel internal constructor(
    plantRepository: PlantRepository,
    private val savedStateHandle: SavedStateHandle // 为什么要设置这个参数，为什么与其他VM的参数不同
) : ViewModel() {

    val plants: LiveData<List<Plant>> = getSavedGrowZoneNumber().switchMap {
        if (it == NO_GROW_ZONE) {
            plantRepository.getPlants()
        } else {
            plantRepository.getPlantsWithGrowZoneNumber(it)
        }
    }

    private fun getSavedGrowZoneNumber(): MutableLiveData<Int> {
        return savedStateHandle.getLiveData(
            GROW_ZONE_SAVED_STATE_KEY,
            NO_GROW_ZONE
        )

    }

    fun setGrowZoneNumber(num: Int) {
        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, num)
    }

    // 清除设置
    fun clearGrowZoneNumber() {
        savedStateHandle.set(
            GROW_ZONE_SAVED_STATE_KEY,
            NO_GROW_ZONE
        )
    }

    fun isFiltered() = getSavedGrowZoneNumber().value != NO_GROW_ZONE

    companion object {
        private const val NO_GROW_ZONE = -1
        private const val GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY"
    }


}