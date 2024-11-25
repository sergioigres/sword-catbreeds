package com.swordcatbreedsapplication.feature.breed

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.swordcatbreedsapplication.api.CatApi
import com.swordcatbreedsapplication.api.model.CatBreed
import com.swordcatbreedsapplication.data.DummyCatBreed
import com.swordcatbreedsapplication.data.CatRepository


// Create an instance of CatApi
val catApi = CatApi.create()

// Create an instance of CatRepository
val catRepository = CatRepository(catApi)

// Create an instance of CatBreedsViewModel
val catBreedViewModel = CatBreedViewModel(catRepository)

const val SCREE_NAME = "Cat Breed"

@Composable
fun BreedScreen(itemId: String, navController: NavHostController) {

    val scrollState = rememberScrollState()

    // Observe cats StateFlow
    val catBreed by catBreedViewModel.catBreed.collectAsState()
    // Fetch cat breeds when the screen is first displayed
    LaunchedEffect(Unit) {
        catBreedViewModel.fetchCatBreed("abys")
    }
    //
    CatBreedDetails(
        scrollState, catBreed, isFavorite = false
    )

    Button(onClick = { navController.navigate("home") }) {
        Text("Go to Home")
    }

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
            // Image
            CatBreedImage(catBreed)
            // Favorite Button
            CatBreedFavoriteButton(isFavorite)
            // Description
            CatBreedDescription(catBreed)
        }
    }

}

@Composable
private fun CatBreedImage(cat: CatBreed) {
    //
    AsyncImage(
        // cat.Image?.url
        model = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg",
        contentDescription = "Image of ${cat.name}, a ${cat.origin} cat",
        modifier = Modifier
            .fillMaxSize()
            //.clip(CircleShape)
            .border(1.5.dp, MaterialTheme.colorScheme.primary)
        //.align(Alignment.CenterHorizontally)
    )
}

@Composable
private fun CatBreedFavoriteButton(isFavorite: Boolean) {
    //val addContentDescription = stringResource(R.string.desc)
    val context = LocalContext.current
    FloatingActionButton(
        onClick = {
            Toast.makeText(context, "Add to Favourite", Toast.LENGTH_SHORT).show()
            //plantDetailsViewModel.addPlantToGarden()
        },
        shape = MaterialTheme.shapes.small,
        //modifier = Modifier.align(Alignment.CenterHorizontally)
    ) {
        Icon(
            Icons.Filled.Add, contentDescription = null
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

