package com.swordcatbreedsapplication.feature.breeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swordcatbreedsapplication.api.CatBreedJson
import com.swordcatbreedsapplication.api.CatApi
import com.swordcatbreedsapplication.data.CatBreed
import com.swordcatbreedsapplication.data.CatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//private val breedsRepository: BreedsRepository
class CatBreedsViewModel(private val repository: CatRepository) : ViewModel() {

    // MutableStateFlow to hold the list of cat breeds
    private val _catBreeds = MutableStateFlow<List<CatBreedJson>>(emptyList())

    // StateFlow to hold the list of cat breeds
    val catBreeds: StateFlow<List<CatBreedJson>> = _catBreeds

    // Fetch cat breeds from the repository
    fun fetchCatBreeds() {
        viewModelScope.launch {
            // Fetch cat breeds from the repository
            _catBreeds.value = repository.fetchCatBreeds()
        }
    }

    /*
    companion object {
        val items: StateFlow<List<CatBreed>> = MutableStateFlow(CatApi.getCatBreeds())
    }
     */

    /*
    val items: StateFlow<List<CatBreed>> =
        gardenPlantingRepository
            .getPlantedGardens()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )
     */

}
