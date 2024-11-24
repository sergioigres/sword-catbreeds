package com.swordcatbreedsapplication.feature.favorites

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.swordcatbreedsapplication.data.CatBreed
import com.swordcatbreedsapplication.ui.theme.CatBreedsTheme

@Composable
fun FavCatBreedCard(cat: CatBreed) {
    Row {
        // Cat Details
        Column(modifier = Modifier.padding(all = 8.dp)) {
            // Cat Image
            AsyncImage(
                model = cat.image,
                contentDescription = "Image of ${cat.name}, a ${cat.origin} cat",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary)
                    .align(Alignment.CenterHorizontally)
            )
            //
            Spacer(modifier = Modifier.height(8.dp))
            // Labels
            Text(
                text = cat.name,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = cat.origin,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
        }
        /*
        // Space
        Spacer(modifier = Modifier.width(8.dp))
        // Cat Details
        Column {
            // Cat Image
            AsyncImage(
                model = cat.image,
                contentDescription = "Cat image",
            )
            Text(text = "Name: ${cat.name}!", modifier = modifier)
            Text(text = "Origin: ${cat.origin}!", modifier = modifier)
        }
        */

    }
}

@Composable
fun ListOfCats(cats: List<CatBreed>) {
    LazyColumn {
        items(cats) { message ->
            FavCatBreedCard(message)
        }
    }
}

// Preview
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

@Preview
@Composable
fun PreviewConversation() {
    CatBreedsTheme {
        ListOfCats(DummyLocalData.listOfCatBreeds)
    }
}

@Preview(showBackground = true)
@Composable
fun CatCardPreview() {
    CatBreedsTheme {
        FavCatBreedCard(
            CatBreed(
                "https://media.istockphoto.com/id/1443562748/photo/cute-ginger-cat.jpg?s=612x612&w=0&k=20&c=vvM97wWz-hMj7DLzfpYRmY2VswTqcFEKkC437hxm3Cg=",
                "Abyssinian",
                "Egypt",
                "description",
                "temperament"
            )
        )
    }
}