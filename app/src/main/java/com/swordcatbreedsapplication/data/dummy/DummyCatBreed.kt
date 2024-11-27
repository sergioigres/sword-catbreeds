package com.swordcatbreedsapplication.data.dummy

import com.swordcatbreedsapplication.data.api.models.CatBreed
import com.swordcatbreedsapplication.data.api.models.Image
import kotlin.String

data class DummyCatBreed(
    val image: String,
    val name: String,
    val origin: String,
    val description: String,
    val temperament: String
)

fun DummyCatBreed.toDomain(): CatBreed {
    return CatBreed(
        name = this.name,
        origin = this.origin,
        description = this.description,
        temperament = this.temperament,
        image = Image(url = this.image)
    )
}