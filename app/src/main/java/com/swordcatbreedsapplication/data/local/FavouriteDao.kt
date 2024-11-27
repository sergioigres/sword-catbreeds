package com.swordcatbreedsapplication.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {

    // Get all
    @Query("select * from favorite_cat_breeds")
    fun getAll(): Flow<List<CatBreedEntity>>

    // Add cat breed
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(catBreedEntity: CatBreedEntity)

    // Remove cat breed
    @Delete
    suspend fun remove(catBreedEntity: CatBreedEntity)

    // Check if cat breed is favorite
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_cat_breeds WHERE id = :catBreedId LIMIT 1)")
    fun isFavorite(catBreedId: String): Flow<Boolean>

}