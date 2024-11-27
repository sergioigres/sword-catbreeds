package com.swordcatbreedsapplication.feature.breed

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.swordcatbreedsapplication.data.api.CatApi
import com.swordcatbreedsapplication.data.api.models.CatBreed
import com.swordcatbreedsapplication.data.CatRepository


// Create an instance of CatApi
val catApi = CatApi.create()

// Create an instance of CatRepository
val catRepository = CatRepository(catApi)

// Create an instance of CatBreedsViewModel
val catBreedViewModel = CatBreedViewModel(catRepository)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BreedScreen(
    itemId: String,
    navController: NavHostController,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit
) {

    val scrollState = rememberScrollState()


    /*
    // TODO - Add to favourites using dummy data
    val isFavorite = favoritesViewModel.isFavorite(itemId)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )
     */

    // Observe cats StateFlow
    val catBreed by catBreedViewModel.catBreed.collectAsState()
    // Fetch cat breeds when the screen is first displayed
    LaunchedEffect(Unit) {
        catBreedViewModel.fetchCatBreed("abys")
    }

    // Scaffold
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SetupTopBar(
                navController,
                onShareClick,
                onBackClick
            )
        },
        floatingActionButton = {
            CatBreedFavoriteButton(isFavorite = false)
        }
    ) { innerPadding ->
        // Content
        CatBreedDetails(
            scrollState, catBreed, isFavorite = false
        )
    }


}

@ExperimentalMaterial3Api
@Composable
fun SetupTopBar(
    navController: NavHostController,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit
) {

    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = { },
        navigationIcon = {
            IconButton(
                onClick = { onBackClick() }
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onShareClick() }
            ) {
                Icon(Icons.Default.Share, contentDescription = "Share")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
private fun CatBreedDetails(
    scrollState: ScrollState,
    catBreed: CatBreed,
    isFavorite: Boolean
) {
    //
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.verticalScroll(scrollState)) {
            Box(Modifier.fillMaxSize()) {
                // Image
                CatBreedImage(catBreed)
                // Favorite Button
                //CatBreedFavoriteButton(isFavorite)
            }
            // Description
            CatBreedDescription(catBreed)
        }
    }

}

@Composable
private fun CatBreedImage(cat: CatBreed) {
    //
    AsyncImage(
        model = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg", // TODO - Replace with cat.image.url
        contentDescription = "Image of ${cat.name}, a ${cat.origin} cat",
        modifier = Modifier
            .fillMaxSize()
            .border(1.5.dp, MaterialTheme.colorScheme.primary)
    )
}

@Composable
private fun CatBreedFavoriteButton(isFavorite: Boolean) {
    //val addContentDescription = stringResource(R.string.desc)
    val context = LocalContext.current
    SmallFloatingActionButton(
        onClick = {
            Toast.makeText(context, "Add to Favourite", Toast.LENGTH_SHORT).show()
            /*
            // TODO - Add to favourites using dummy data
            // Dummy data
            val dummyData = DummyLocalData.listOfCatBreeds[0]
            // Simulate adding an item (replace with user input or other logic)
            favoritesViewModel.add(
                name = dummyData.name,
                description = dummyData.description,
                image = dummyData.image,
                origin = dummyData.origin,
                temperament = dummyData.temperament
            )
             */
        },
        shape = CircleShape
    ) {
        Icon(
            Icons.Filled.Favorite, contentDescription = null
        )
    }
}


// TODO - Create dimensions for padding
@Composable
private fun CatBreedDescription(catBreed: CatBreed) {
    Column(modifier = Modifier.padding(24.dp)) {
        // Cat Breed Name
        Text(
            text = catBreed.name,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
                .align(Alignment.CenterHorizontally)
        )
        // Cat Breed Temperament
        Box(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
        ) {
            Column(Modifier.fillMaxWidth()) {
                Text(
                    text = "Temperament",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = catBreed.temperament,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
        // Cat Breed Description
        Text(
            text = catBreed.description,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 16.dp
                )
                .align(Alignment.CenterHorizontally)
        )
    }
}

