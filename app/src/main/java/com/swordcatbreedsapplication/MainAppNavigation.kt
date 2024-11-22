package com.swordcatbreedsapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swordcatbreedsapplication.feature.breed.BreedScreen
import com.swordcatbreedsapplication.feature.breeds.CatBreedsScreen
import com.swordcatbreedsapplication.feature.favorites.FavouritesScreen

@Composable
fun MainAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        // Case home
        composable("home") { CatBreedsScreen(navController) }
        // Case details
        composable("details") { BreedScreen("0",navController) }
        // Case favorites
        composable("favorites") { FavouritesScreen(navController) }
    }
}


