package com.swordcatbreedsapplication.feature.breeds

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.swordcatbreedsapplication.api.CatApi
import com.swordcatbreedsapplication.data.CatRepository

// Create an instance of CatApi
val catApi = CatApi.create()

// Create an instance of CatRepository
val catRepository = CatRepository(catApi)

// Create an instance of CatBreedsViewModel
val catBreedsViewModel = CatBreedsViewModel(catRepository)

const val SCREE_NAME = "Cat Breeds"

@Composable
fun CatBreedsScreen(
    navController: NavHostController,
    onCatBreedClick: () -> Unit,
    onFavClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    // Scaffold
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { SetupTopBar(title = SCREE_NAME, onFavClick, onSearchClick) },
        //bottomBar = { setBottomBar() },
        //floatingActionButton = { setFloatingActionButton() },
    ) { innerPadding ->
        // Content
        CatListScreen(
            navController,
            modifier = Modifier.padding(innerPadding),
            catBreedsViewModel,
            onCatBreedClick
        )
    }
}

@Composable
fun CatListScreen(
    navController: NavHostController,
    modifier: Modifier,
    catBreedsViewModel: CatBreedsViewModel,
    onCatBreedClick: () -> Unit
) {
    // Observe cats StateFlow
    val catBreeds by catBreedsViewModel.catBreeds.collectAsState()
    // Fetch cat breeds when the screen is first displayed
    LaunchedEffect(Unit) {
        catBreedsViewModel.fetchCatBreeds()
    }
    // Use LazyColumn to display the list of cats
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        //contentPadding = PaddingValues(16.dp)
    ) {
        items(catBreeds.size)
        { index ->
            CatBreedCard(
                catBreeds[index],
                onCatBreedClick
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupTopBar(
    title: String,
    onFavClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = {
                onFavClick()
                //Toast.makeText(context, "Favorites", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Favorite, contentDescription = "Favorites")
            }
            IconButton(onClick = {
                onSearchClick()
                //Toast.makeText(context, "Search clicked", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
    )
}



