package com.swordcatbreedsapplication.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DATABASE_NAME = "favorite_cat_breeds_db"

// Create database
@Database(entities = [CatBreedEntity::class], version = 2)
abstract class CatBreedDatabase : RoomDatabase() {

    abstract fun favouriteDao(): FavouriteDao


    // Create singleton
    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: CatBreedDatabase? = null

        // Initialize database
        fun init(context: Context) {
            getDatabase(context)
        }

        // Get database
        fun getDatabase(context: Context): CatBreedDatabase {
            return instance ?: synchronized(this) {
                val db = Room.databaseBuilder(
                    context,
                    CatBreedDatabase::class.java,
                    DATABASE_NAME
                ).build()
                instance = db
                db
            }
        }

    }




}