package com.lq.he.sum.gplant.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lq.he.sum.data.GardenPlantingRepository

// 工厂类
class GardenPlantingListViewModelFactory(
    private val repository: GardenPlantingRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GardenPlantingListViewModel(repository) as T
    }
}