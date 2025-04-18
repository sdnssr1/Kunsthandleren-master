package com.example.kunsthandleren.network.dto

data class PhotoDto(
    val id: String,
    val title: String,
    val imageThumbUrl: String,
    val imageUrl: String,
    val artistId: String,
    val categoryId: String,
    val price: Float
)
