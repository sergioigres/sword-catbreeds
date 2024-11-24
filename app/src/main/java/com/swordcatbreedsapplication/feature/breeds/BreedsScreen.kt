package com.swordcatbreedsapplication.feature.breeds

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {

//    CatBreedsTheme {
        Scaffold(
            //topBar = { setTopBar() },
            //bottomBar = { setBottomBar() },
            //floatingActionButton = { setFloatingActionButton() },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            CatListScreen(
                navController, modifier = Modifier.padding(innerPadding)

                /*
                uiCatCard(
                    CatBreed(
                        "https://media.istockphoto.com/id/1443562748/photo/cute-ginger-cat.jpg?s=612x612&w=0&k=20&c=vvM97wWz-hMj7DLzfpYRmY2VswTqcFEKkC437hxm3Cg=",
                        "Abyssinian",
                        "Egypt",
                        "description",
                        "temperament"
                    ), modifier = Modifier.padding(innerPadding)
                    */
            )
        }


//    }
}


@Composable
fun CatListScreen(navController: NavHostController, modifier: Modifier) {
    // Observe cats StateFlow
    val listOfCatBreeds by BreedsViewModel.items.collectAsState()
    // Use LazyColumn to display the list of cats
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(listOfCatBreeds) { item ->
            CatBreedCard(item)//, Modifier.fillMaxSize())
            //CatListItem(item, onCatClick)
            Divider() // Optional divider between items
        }
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
