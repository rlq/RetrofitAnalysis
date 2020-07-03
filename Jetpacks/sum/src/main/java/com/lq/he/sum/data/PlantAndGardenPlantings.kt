package com.lq.he.sum.data

import androidx.room.Embedded
import androidx.room.Relation

// 数据类，连接VM与DB
data class PlantAndGardenPlantings(
    @Embedded
    val plant: Plant,

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    val gardenPlantings: List<GardenPlanting> = emptyList()
) {
}