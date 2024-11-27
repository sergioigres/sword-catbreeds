package com.swordcatbreedsapplication.feature.favorites

import android.annotation.SuppressLint
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.swordcatbreedsapplication.data.FavoriteRepository
import com.swordcatbreedsapplication.data.api.models.CatBreed
import com.swordcatbreedsapplication.data.dummy.DummyCatBreed
import com.swordcatbreedsapplication.feature.UiState

// Create an instance of CatRepository
val favRepository = FavoriteRepository()

// Create an instance of CatBreedsViewModel
val favViewModel = FavoritesViewModel(favRepository)

const val SCREE_NAME = "Favorites"

@SuppressLint("UnusedCrossfadeTargetStateParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    navController: NavHostController,
    onCatBreedClick: () -> Unit,
    // onBackPressed: () -> Unit
) {

    /*
    // Initialize Room database
    val favouriteDao = CatBreedDatabase.getDatabase(LocalContext.current).favouriteDao()
    // Create an instance of FavoriteRepository
    val favoriteRepository = FavoriteRepository(favouriteDao)
    // Crate an instance of FavoritesViewModel
    val favoritesViewModel = FavoritesViewModel(favoriteRepository)
    // Observe favorites StateFlow
    val favorites by favoritesViewModel.favorites.collectAsState()
    // Fetch cat breeds when the screen is first displayed
    LaunchedEffect(Unit) {
        favoritesViewModel.favorites
    }
     */

    // Scaffold
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SetupTopBar(
                title = SCREE_NAME,
                navController,
                // onBackPressed // TODO Fix this
            )
        },
    ) { innerPadding ->
        // Content
        FavoritesContent(
            navController,
            Modifier.fillMaxSize(),
            favViewModel,
            onCatBreedClick
        )
    }


    /*


    // Observe cats StateFlow
    //val favorites = DummyLocalData.listOfCatBreeds
    val favorites = emptyList<DummyCatBreed>()
    // Validate if favorites is empty
    if (favorites.isEmpty()) {
        // Create empty screen view
        Text("No favorites yet")
    } else {
        // Create list of favorites
        Column(modifier = Modifier.fillMaxSize()) {
            Text("Top Bar")
            LazyColumn {
                items(favorites.size) { index ->
                    FavCard(favorites[index])
                }
            }
        }
    }

     */


    /*
Scaffold(
    //
    topBar = {
        TopAppBar(title = { Text("Favorite Cat Breeds") })
    },
    /*
    //

        ) {
            Icon(Icons.Default.Favorite, contentDescription = "Add")
        }
    },
     */
    modifier = Modifier.fillMaxSize()
) { innerPadding ->



    //
    LazyColumn {
        items(favorites.size) { index ->
            FavCard(favorites[index])
        }
    }


    }

     */
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupTopBar(
    title: String,
    navController: NavHostController,
    // onBackClick: () -> Unit
) {

    TopAppBar(title = { Text(text = title) },
        navigationIcon = {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Composable
fun FavoritesContent(
    navController: NavHostController,
    modifier: Modifier,
    favViewModel: FavoritesViewModel,
    onCatBreedClick: () -> Unit
) {
    // Observe favorites StateFlow
    val uiState by favViewModel.favorites.collectAsState()
    // Fetch cat breeds when the screen is first displayed
    Crossfade(targetState = uiState) { state ->
        when (uiState) {
            is UiState.Loading -> LoadingView()
            is UiState.Success -> SuccessView(
                catBreeds = (uiState as UiState.Success<List<CatBreed>>).data,
                modifier = modifier,
                onCatBreedClick = onCatBreedClick
            )

            is UiState.Empty -> EmptyView("No favorites yet")
        }
    }
}


@Composable
fun SuccessView(
    modifier: Modifier, catBreeds: List<CatBreed>, onCatBreedClick: () -> Unit
) {
    // Use LazyColumn to display the list of cats
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = modifier,
        //contentPadding = PaddingValues(16.dp)
    ) {
        items(catBreeds.size) { index ->
            FavCatBreedCard(
                catBreeds[index], onCatBreedClick
            )
        }
    }
}

@Composable
fun FavCard(catBreed: DummyCatBreed) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = catBreed.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = catBreed.description,
                style = MaterialTheme.typography.bodySmall
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
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text
        )
    }
}