package com.swordcatbreedsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.swordcatbreedsapplication.ui.theme.CatBreedsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO Room database
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