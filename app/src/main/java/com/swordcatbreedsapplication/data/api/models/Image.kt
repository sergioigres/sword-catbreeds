package com.swordcatbreedsapplication.data.api.models

data class Image(
    val id: String = "",
    val width: Int = Int.MIN_VALUE,
    val height: Int = Int.MIN_VALUE,
    var url: String = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
)