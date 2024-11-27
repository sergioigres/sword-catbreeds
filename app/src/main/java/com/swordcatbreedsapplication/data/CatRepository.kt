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

}