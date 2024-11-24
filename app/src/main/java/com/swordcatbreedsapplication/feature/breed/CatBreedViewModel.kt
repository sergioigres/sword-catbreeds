package com.swordcatbreedsapplication.feature.breed

import androidx.lifecycle.ViewModel
import com.swordcatbreedsapplication.api.BreedsRepository
import com.swordcatbreedsapplication.data.CatBreed
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class CatBreedViewModel() : ViewModel() {
    companion object {
        val items: StateFlow<CatBreed> = MutableStateFlow(BreedsRepository.getCatBreed(0))
    }
}
