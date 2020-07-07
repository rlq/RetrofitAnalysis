package com.lq.he.sum.data

// 这是plant的DB 操作类
class PlantRepository private constructor(
    private val plantingDao: PlantDao
){

    fun getPlants() = plantingDao.getPlants()

    fun getPlant(plantId: String) = plantingDao.getPlant(plantId)

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) =
        plantingDao.getPlantsWithGrowZoneNumber(growZoneNumber)

    companion object {
        @Volatile
        private var instance: PlantRepository? = null

        fun getInstance(plantingDao: PlantDao) =
            instance ?: synchronized(this) {
                instance ?: PlantRepository(plantingDao).also { instance = it }
            }
    }
}