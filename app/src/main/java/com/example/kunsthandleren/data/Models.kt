package com.example.kunsthandleren.data

data class Bilde(
    val id: Int,
    val navn: String,
    val kategori: Kategori
)

enum class Kategori {
    Dyr, Mat, Sport, Abstrakt
}

data class Kunstner(
    val id: Int,
    val navn: String
)
