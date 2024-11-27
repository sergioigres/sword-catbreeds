package com.swordcatbreedsapplication.feature.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swordcatbreedsapplication.data.api.models.CatBreed
import com.swordcatbreedsapplication.data.CatRepository
import com.swordcatbreedsapplication.feature.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//private val breedsRepository: BreedsRepository
class CatBreedsViewModel(private val repository: CatRepository) : ViewModel() {

    // MutableStateFlow to hold the list of cat breeds
    private val _catBreeds = MutableStateFlow<UiState<List<CatBreed>>>(UiState.Loading)

    // StateFlow to hold the list of cat breeds
    val catBreeds: StateFlow<UiState<List<CatBreed>>> = _catBreeds

    init {
        fetchCatBreeds()
    }

    // Fetch cat breeds from the repository
    fun fetchCatBreeds() {
        // Simulate a loading process
        _catBreeds.value = UiState.Loading

        viewModelScope.launch {
            // Simulate a delay for loading
            delay(1000)
            // Fetch cat breeds from the repository
            val data = repository.fetchCatBreeds()
            if (data.isEmpty())
                _catBreeds.value = UiState.Empty
            else
                _catBreeds.value = UiState.Success(data)
        }
    }
}
