package com.lq.he.sum.pad.vm

import com.lq.he.sum.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.*

// 业务逻辑，种植时间，浇水时间
class PlantAndGardenPlantingsViewModel(plantings: PlantAndGardenPlantings) {

    private val plant = checkNotNull(plantings.plant)
    private val gardenPlanting = plantings.gardenPlantings[0]

    // 这里出了个crash，只有Calendar.time才能format
    val waterDateString: String = dateFormat.format(gardenPlanting.lastWateringDate.time)

    val wateringInterval
        get() = plant.wateringInterval

    val imageUrl
        get() = plant.imageUrl

    val plantName
        get() = plant.name

    val plantDateString: String = dateFormat.format(gardenPlanting.plantDate.time)

    val plantId
        get() = plant.plantId


    companion object {
        val dateFormat = SimpleDateFormat("MMM d, yyy", Locale.CHINA)
    }
}