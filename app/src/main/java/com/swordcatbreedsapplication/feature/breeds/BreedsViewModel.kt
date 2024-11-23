package com.swordcatbreedsapplication.feature.breeds

import androidx.lifecycle.ViewModel
import com.swordcatbreedsapplication.api.BreedsRepository
import com.swordcatbreedsapplication.data.CatBreed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//private val breedsRepository: BreedsRepository
class BreedsViewModel() : ViewModel() {
    //public val items: StateFlow<List<CatBreed>> = MutableStateFlow(BreedsRepository.getCatBreeds())

    companion object {
        val items: StateFlow<List<CatBreed>> = MutableStateFlow(BreedsRepository.getCatBreeds())
    }
}
