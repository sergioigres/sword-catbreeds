package com.swordcatbreedsapplication.feature.breeds

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.swordcatbreedsapplication.api.CatApi
import com.swordcatbreedsapplication.data.CatRepository

//val retrofit = CatBreedsApi.create()
val catApi = CatApi.create() //NetworkModule.provideCatApi(retrofit)
val catRepository = CatRepository(catApi)
val catBreedsViewModel = CatBreedsViewModel(catRepository)

@Composable
fun CatBreedsScreen(navController: NavHostController) {

    Scaffold(
        //topBar = { setTopBar() },
        //bottomBar = { setBottomBar() },
        //floatingActionButton = { setFloatingActionButton() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        CatListScreen(
            navController, modifier = Modifier.padding(innerPadding),
            catBreedsViewModel
        )
    }
}

@Composable
fun CatListScreen(
    navController: NavHostController,
    modifier: Modifier,
    catBreedsViewModel: CatBreedsViewModel
) {
    // Observe cats StateFlow
    val catBreeds by catBreedsViewModel.catBreeds.collectAsState()
    // Fetch cat breeds when the screen is first displayed
    LaunchedEffect(Unit) {
        catBreedsViewModel.fetchCatBreeds()
    }
    // Use LazyColumn to display the list of cats
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(catBreeds.size) { index ->
            CatBreedCard(catBreeds[index])
            Divider()
        }
        /*
        items(catBreeds) { item ->
            CatBreedCard(item)//, Modifier.fillMaxSize())
            //CatListItem(item, onCatClick)
            //Divider() // Optional divider between items
        }
         */
    }

    // TEST
    Button(onClick = { navController.navigate("details") }) {
        Text("Go to Details")
    }

    // TEST
    Button(onClick = { navController.navigate("favorites") }) {
        Text("Go to Favorites")
    }
}
