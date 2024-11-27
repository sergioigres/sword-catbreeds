package com.swordcatbreedsapplication.data

import com.swordcatbreedsapplication.data.api.models.CatBreed
import com.swordcatbreedsapplication.data.api.CatApi

class CatRepository(private val catBreedsApi: CatApi) {

    // Fetch cat breeds
    suspend fun fetchCatBreeds(): List<CatBreed> {
        return try {
            catBreedsApi.getCatBreeds()
        } catch (exp: Exception) {
            // TODO Handle the exception
            emptyList()
        }
    }

    // Fetch cat breed
    suspend fun fetchCatBreed(breedId: String): CatBreed {
        return catBreedsApi.getCatBreed(breedId)
    }


    /*
   companion object {

       private const val PAGE_SIZE = 25

       // For Singleton instantiation
       @Volatile private var instance: CatBreedsRepository? = null


       fun getInstance(gardenPlantingDao: GardenPlantingDao) =
           instance ?: synchronized(this) {
               instance ?: CatBreedsRepository(gardenPlantingDao).also { instance = it }
           }


    }

     */


}