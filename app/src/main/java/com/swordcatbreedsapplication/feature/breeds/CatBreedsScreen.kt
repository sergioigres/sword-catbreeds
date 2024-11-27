package com.swordcatbreedsapplication.feature.breeds

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.swordcatbreedsapplication.data.api.CatApi
import com.swordcatbreedsapplication.data.CatRepository
import com.swordcatbreedsapplication.data.api.models.CatBreed
import com.swordcatbreedsapplication.feature.UiState

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
    onFavoritesClick: () -> Unit,
    onSearchClick: () -> Unit,
) {
    // Scaffold
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { SetupTopBar(title = SCREE_NAME, onFavoritesClick, onSearchClick) },
        //bottomBar = { setBottomBar() },
        //floatingActionButton = { setFloatingActionButton() },
    ) { innerPadding ->
        // Content
        CatBreedsContent(
            navController,
            modifier = Modifier.padding(innerPadding),
            catBreedsViewModel,
            onCatBreedClick
        )
    }
}

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun CatBreedsContent(
    navController: NavHostController,
    modifier: Modifier,
    catBreedsViewModel: CatBreedsViewModel,
    onCatBreedClick: () -> Unit
) {
    // UiState
    val uiState by catBreedsViewModel.catBreeds.collectAsState()
    // Observe StateFlow
    Crossfade(targetState = uiState) { state ->
        when (uiState) {
            is UiState.Empty -> EmptyView("No cat breeds found")
            is UiState.Loading -> LoadingView()
            is UiState.Success -> SuccessView(
                catBreeds = (uiState as UiState.Success<List<CatBreed>>).data,
                modifier = modifier,
                onCatBreedClick = onCatBreedClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupTopBar(
    title: String, onFavClick: () -> Unit, onSearchClick: () -> Unit
) {
    TopAppBar(title = { Text(text = title) }, actions = {
        IconButton(onClick = {
            onFavClick()
        }) {
            Icon(Icons.Default.Favorite, contentDescription = "Favorites")
        }
        IconButton(onClick = {
            onSearchClick()
        }) {
            Icon(Icons.Default.Search, contentDescription = "Search")
        }
    })
}

@Composable
fun SuccessView(
    modifier: Modifier, catBreeds: List<CatBreed>, onCatBreedClick: () -> Unit
) {
    // Use LazyColumn to display the list of cats
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        //contentPadding = PaddingValues(16.dp)
    ) {
        items(catBreeds.size) { index ->
            CatBreedCard(
                catBreeds[index], onCatBreedClick
            )
        }
    }
}


@Composable
fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyView(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = text
        )
    }
}