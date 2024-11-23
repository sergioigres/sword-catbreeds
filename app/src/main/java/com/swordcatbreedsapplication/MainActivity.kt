package com.swordcatbreedsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swordcatbreedsapplication.api.BreedsRepository
import com.swordcatbreedsapplication.data.CatBreed
import com.swordcatbreedsapplication.feature.breeds.BreedsViewModel
import com.swordcatbreedsapplication.ui.theme.SwordCatBreedsApplicationTheme
import com.swordcatbreedsapplication.ui.theme.uiCatCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            // Navigation
            val navController = rememberNavController()
            MainAppNavigation(navController = navController)

            // Provide the ViewModel
            val breedsViewModel = BreedsViewModel()
            //CatListScreen(breedsViewModel)


            /*
            SwordCatBreedsApplicationTheme {
                Scaffold(
                    //topBar = { setTopBar() },
                    //bottomBar = { setBottomBar() },
                    //floatingActionButton = { setFloatingActionButton() },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    uiCatCard(
                        CatBreed(
                            "https://media.istockphoto.com/id/1443562748/photo/cute-ginger-cat.jpg?s=612x612&w=0&k=20&c=vvM97wWz-hMj7DLzfpYRmY2VswTqcFEKkC437hxm3Cg=",
                            "Abyssinian",
                            "Egypt",
                            "description",
                            "temperament"
                        ), modifier = Modifier.padding(innerPadding)
                    )
                }


            }

             */
        }
    }
}

@Composable
fun CatListScreen(
    breedsViewModel: BreedsViewModel
    //, // Inject ViewModel instance
    //onCatClick: (CatBreed) -> Unit // Lambda to handle cat item clicks
) {
    // Observe cats StateFlow
    val listOfCatBreeds by BreedsViewModel.items.collectAsState()
    // Use LazyColumn to display the list of cats
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(listOfCatBreeds) { item ->
            uiCatCard(item)//, Modifier.fillMaxSize())
            //CatListItem(item, onCatClick)
            Divider() // Optional divider between items
        }
    }
}



@Composable
fun CatListItem(i: Int, click: (CatBreed) -> Unit) {
    TODO("Not yet implemented")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun setTopBar(): @Composable () -> Unit {
    return {
        TopAppBar(
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            title = {
                Text("Top app bar")
            }
        )
    }
}

@Composable
fun setBottomBar(): @Composable () -> Unit {
    return {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.primary,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Bottom app bar",
            )
        }
    }
}

@Composable
fun setFloatingActionButton() {
    return FloatingActionButton(onClick = { /* do something */ }) {
        Icon(Icons.Filled.Add, "Localized description")
    }
}