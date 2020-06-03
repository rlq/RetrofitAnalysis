package com.lq.he.sum.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_planting")
    fun getGardenPlantings(): LiveData<List<GardenPlanting>>

    @Query("SELECT EXISTS (SELECT 1 FROM garden_planting WHERE plant_id = :plantId LIMIT 1)")
    fun isPlanted(plantId: String): LiveData<Boolean>

    @Transaction
    @Query("SELECT * FROM plants WHERE id IN (SELECT DISTINCT(plant_id) FROM garden_planting)")
    fun getPlantedGardens(): LiveData<List<PlantAndGardenPlantings>>

    @Insert
    suspend fun insertGardenPlanting(gardenPlanting: GardenPlanting): Long

    @Insert
    suspend fun deleteGardenPlanting(gardenPlanting: GardenPlanting)

}