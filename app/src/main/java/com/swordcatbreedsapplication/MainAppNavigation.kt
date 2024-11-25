package com.swordcatbreedsapplication

import android.app.Activity
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swordcatbreedsapplication.feature.breed.BreedScreen
import com.swordcatbreedsapplication.feature.breeds.CatBreedsScreen
import com.swordcatbreedsapplication.feature.favorites.FavouritesScreen

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val deepLinks: List<NavDeepLink> = emptyList(),
) {
    object CatBreeds : Screen("catBreeds")
    object CatBreed : Screen("catBreed")
    object Favorites : Screen("favorites")
}

@Composable
fun MainAppNavigation() {

    val navController = rememberNavController()

    val activity = LocalContext.current as Activity

    var onCatBreedClick = {
        navController.navigate(Screen.CatBreed.route)
    }

    var onFavoritesClick = {
        navController.navigate(Screen.Favorites.route)
    }

    var onSearchClick = {
        Toast.makeText(activity, "Search clicked", Toast.LENGTH_SHORT).show()
    }

    NavHost(navController = navController, startDestination = Screen.CatBreeds.route) {
        // Case home
        composable(Screen.CatBreeds.route) {
            CatBreedsScreen(
                navController,
                onCatBreedClick,
                onFavoritesClick,
                onSearchClick
            )
        }
        // Case details
        composable(Screen.CatBreed.route) { BreedScreen("0", navController) }
        // Case favorites
        composable(Screen.Favorites.route) { FavouritesScreen(navController) }
    }
}


