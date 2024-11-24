package com.swordcatbreedsapplication.data

import com.swordcatbreedsapplication.api.CatBreedJson
import com.swordcatbreedsapplication.api.CatApi

class CatRepository(private val catBreedsApi: CatApi) {

    // Fetch cat breeds
    suspend fun fetchCatBreeds(): List<CatBreedJson> {
        return try {
            catBreedsApi.getCatBreeds()
        } catch (exp: Exception) {
            // TODO Handle the exception
            emptyList()
        }
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