package com.swordcatbreedsapplication.feature.breed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swordcatbreedsapplication.data.api.models.CatBreed
import com.swordcatbreedsapplication.data.CatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CatBreedViewModel(private val repository: CatRepository) : ViewModel() {

    // MutableStateFlow to hold the cat breed
    private val _catBreed = MutableStateFlow(CatBreed())

    // StateFlow to hold the cat breed
    val catBreed: StateFlow<CatBreed> = _catBreed

    // Fetch cat breed from the repository
    fun fetchCatBreed(breedId: String) {
        viewModelScope.launch {
            // Fetch cat breeds from the repository
            _catBreed.value = repository.fetchCatBreed(breedId)
        }
    }


    /*
    companion object {
        val items: StateFlow<CatBreed> = MutableStateFlow(CatApi.getCatBreed(0))
    }
     */
}
