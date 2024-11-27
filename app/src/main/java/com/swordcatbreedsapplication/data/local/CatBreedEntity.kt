package com.swordcatbreedsapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Create Favorites Table
@Entity(tableName = "favorite_cat_breeds")
data class CatBreedEntity(
    @PrimaryKey val id: String,
    val name: String,
    val image: String,
    val origin: String,
    val description: String,
    val temperament: String
)
