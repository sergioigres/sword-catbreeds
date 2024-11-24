package com.swordcatbreedsapplication.api

import DummyLocalData
import com.swordcatbreedsapplication.data.CatBreed

public class BreedsRepository() {
    //fun getCatBreeds(): List<CatBreed> = DummyLocalData.listOfCatBreeds

    companion object {
        fun getCatBreeds(): List<CatBreed> {
            return DummyLocalData.listOfCatBreeds
        }

        fun getCatBreed(id: Int): CatBreed {
            return DummyLocalData.listOfCatBreeds.get(id)
        }

    }
}

/*
class CatBreedRepository(private val catDao: CatDao) {

    fun getCatBreeds(): Flow<List<CatBreed>> = catDao.getAllCats()
}

 */