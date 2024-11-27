package com.swordcatbreedsapplication

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink
import androidx.navigation.NavHostController
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
    object Home : Screen("catBreeds")
    object CBDetail : Screen("catBreed")
    object Favorites : Screen("favorites")

}

@Composable
fun MainAppNavigation() {
    val navController = rememberNavController()
    MainAppNavigationRoutes(
        navController = navController
    )
}

@Composable
fun MainAppNavigationRoutes(navController: NavHostController) {

    val activity = LocalContext.current as Activity

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        // Case home
        composable(Screen.Home.route) {
            CatBreedsScreen(
                navController,
                onCatBreedClick = {
                    navController.navigate(Screen.CBDetail.route)
                },
                onFavoritesClick = {
                    navController.navigate(Screen.Favorites.route)
                },
                onSearchClick = {
                    Toast.makeText(activity, "Search clicked", Toast.LENGTH_SHORT).show()
                }
            )
        }
        // Case details
        composable(Screen.CBDetail.route) {
            BreedScreen(
                "0", // TODO Get from arguments
                navController,
                onShareClick = {
                    createShareIntent(activity, it.toString())
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        // Case favorites
        composable(Screen.Favorites.route) {
            FavouritesScreen(
                navController,
                onCatBreedClick = {
                    navController.navigate(Screen.CBDetail.route)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

// Helper for sharing
private fun createShareIntent(activity: Activity, name: String) {
    val shareText = "Checkout this cat breed: $name"
    val shareIntent = ShareCompat.IntentBuilder(activity)
        .setText(shareText)
        .setType("text/plain")
        .createChooserIntent()
        .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
    activity.startActivity(shareIntent)
}

