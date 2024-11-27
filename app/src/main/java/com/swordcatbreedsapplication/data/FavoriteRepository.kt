package com.swordcatbreedsapplication.data

import DummyLocalData.toDomainList
import com.swordcatbreedsapplication.data.api.models.CatBreed
import com.swordcatbreedsapplication.data.dummy.DummyCatBreed
import com.swordcatbreedsapplication.data.local.CatBreedEntity
import com.swordcatbreedsapplication.data.local.FavouriteDao
import kotlinx.coroutines.flow.Flow


// TODO Create repository with database interface
//class FavoriteRepository(private val favouriteDao: FavouriteDao) {
class FavoriteRepository() {

    // Fetch dummy cat breeds
    suspend fun fetchFavCatBreeds(): List<CatBreed> {
        return try {
            DummyLocalData.listOfCatBreeds.toDomainList()
        } catch (exp: Exception) {
            // TODO Handle the exception
            emptyList()
        }
    }

    /*
    // Get list of favourite cat breeds
    val favouriteCatBreedsEntity: Flow<List<CatBreedEntity>> = favouriteDao.getAll()

    // Add cat breed
    suspend fun getAll(): Flow<List<CatBreedEntity>> {
        return favouriteDao.getAll()
    }

    // Add cat breed
    suspend fun add(catBreedEntity: CatBreedEntity) {
        favouriteDao.add(catBreedEntity)
    }

    // Remove cat breed
    suspend fun remove(catBreedEntity: CatBreedEntity) {
        favouriteDao.remove(catBreedEntity)
    }

    // Remove cat breed
    suspend fun isFavorite(catBreedEntity: CatBreedEntity) {
        favouriteDao.isFavorite(catBreedEntity.name)
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: FavoriteRepository? = null

        fun getInstance(dao: FavouriteDao) = instance ?: synchronized(this) {
            instance ?: FavoriteRepository(dao).also { instance = it }
        }
    }
     */
}