package com.swordcatbreedsapplication.api

import DummyLocalData
import com.swordcatbreedsapplication.data.CatBreed

public class BreedsRepository() {
    //fun getCatBreeds(): List<CatBreed> = DummyLocalData.listOfCatBreeds

    companion object {
        fun getCatBreeds(): List<CatBreed> {
            return DummyLocalData.listOfCatBreeds
        }
    }
}

/*
class CatBreedRepository(private val catDao: CatDao) {

    fun getCatBreeds(): Flow<List<CatBreed>> = catDao.getAllCats()
}

 */