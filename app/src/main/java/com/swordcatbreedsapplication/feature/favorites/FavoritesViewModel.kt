package com.swordcatbreedsapplication.feature.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swordcatbreedsapplication.data.FavoriteRepository
import com.swordcatbreedsapplication.data.api.models.CatBreed
import com.swordcatbreedsapplication.data.local.CatBreedEntity
import com.swordcatbreedsapplication.feature.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class FavoritesViewModel(private val repository: FavoriteRepository) : ViewModel() {


    // MutableStateFlow to hold the list of cat breeds
    private val _favorites = MutableStateFlow<UiState<List<CatBreed>>>(UiState.Loading)

    // StateFlow to hold the list of cat breeds
    val favorites: StateFlow<UiState<List<CatBreed>>> = _favorites

    init {
        fetchFavCatBreeds()
    }

    // Fetch cat breeds from the repository
    fun fetchFavCatBreeds() {
        // Simulate a loading process
        _favorites.value = UiState.Loading

        viewModelScope.launch {
            // Simulate a delay for loading
            delay(1000)
            // Fetch cat breeds from the repository
            val data = repository.fetchFavCatBreeds()
            if (data.isEmpty())
                _favorites.value = UiState.Empty
            else
                _favorites.value = UiState.Success(data)
        }
    }

    /*
    private val _favorites = MutableStateFlow<List<CatBreedEntity>>(emptyList())

    val favorites: StateFlow<List<CatBreedEntity>> = _favorites.asStateFlow()


    init {
        viewModelScope.launch {
            repository.favouriteCatBreedsEntity.collect { items ->
                _favorites.value = items
            }
        }
    }


    // Fetch cat breeds from the repository
    fun fetchFavoriteCatBreeds() {
        viewModelScope.launch {
            repository.favouriteCatBreedsEntity.collect { items ->
                _favorites.value = items
            }
        }
    }

    fun add(
        image: String,
        name: String,
        origin: String,
        description: String,
        temperament: String
    ) {
        viewModelScope.launch {
            repository.add(
                CatBreedEntity(
                    id = name,
                    name = name,
                    image = image,
                    origin = origin,
                    description = description,
                    temperament = temperament
                )
            )
        }
    }

     */

}
