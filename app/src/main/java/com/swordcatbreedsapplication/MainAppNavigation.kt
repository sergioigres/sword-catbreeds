package com.swordcatbreedsapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swordcatbreedsapplication.feature.breed.BreedScreen
import com.swordcatbreedsapplication.feature.breeds.HomeScreen

@Composable
fun MainAppNavigation(navController: NavHostController) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("details") { BreedScreen("0",navController) }
    }
}


