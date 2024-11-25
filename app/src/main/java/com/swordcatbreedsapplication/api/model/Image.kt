package com.swordcatbreedsapplication.api.model

data class Image(
    val id: String,
    val width: Int,
    val height: Int,
    var url: String
)