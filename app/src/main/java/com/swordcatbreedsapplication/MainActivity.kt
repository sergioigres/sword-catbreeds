package com.swordcatbreedsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.room.Room
import com.swordcatbreedsapplication.data.FavoriteRepository
import com.swordcatbreedsapplication.data.local.CatBreedDatabase
import com.swordcatbreedsapplication.feature.favorites.FavoritesViewModel
import com.swordcatbreedsapplication.ui.theme.CatBreedsTheme
import com.swordcatbreedsapplication.data.local.FavouriteDao

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Room database
        //CatBreedDatabase.getDatabase(applicationContext)
        // Displaying edge-to-edge
        enableEdgeToEdge()
        // Set content of Main Activity
        setContent {
            // Set Theme
            CatBreedsTheme {
                // Navigation
                MainAppNavigation()
            }
        }
    }
}