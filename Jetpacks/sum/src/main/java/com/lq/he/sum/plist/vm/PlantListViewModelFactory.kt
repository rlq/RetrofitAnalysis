package com.lq.he.sum.plist.vm

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.lq.he.sum.data.GardenPlantingRepository
import com.lq.he.sum.data.PlantRepository
import com.lq.he.sum.gplant.vm.GardenPlantingListViewModel

// 植物目录
class PlantListViewModelFactory(
    private val repository: PlantRepository,
    owner: SavedStateRegistryOwner, // 啥意思
    defaultArgs: Bundle? = null
): AbstractSavedStateViewModelFactory(owner, defaultArgs) { // 工厂的抽象类

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        return PlantListViewModel(repository, handle) as T
    }

}

class GardenPlantingListViewModelFactory2(
    private val repository: GardenPlantingRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GardenPlantingListViewModel(repository) as T
    }
}