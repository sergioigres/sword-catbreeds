package com.swordcatbreedsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.swordcatbreedsapplication.data.CatBreed
import com.swordcatbreedsapplication.ui.theme.CatBreedsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Displaying edge-to-edge
        enableEdgeToEdge()
        // Set content of Main Activity
        setContent {
            // Set Theme
            CatBreedsTheme {
                // Navigation
                MainAppNavigation()
            }
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
        TopAppBar(colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Text("Top app bar")
        })
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