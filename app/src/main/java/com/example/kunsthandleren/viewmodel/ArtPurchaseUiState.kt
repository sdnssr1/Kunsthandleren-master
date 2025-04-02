package com.example.kunsthandleren.viewmodel

import com.example.kunsthandleren.Artist
import com.example.kunsthandleren.Category
import com.example.kunsthandleren.Filters
import com.example.kunsthandleren.Photo
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.testPhoto

data class ArtPurchaseUiState (
    val price: Float = 0f,

    val chosenFilter: Filters? = null,
    val chosenArtist: Artist? = null,
    val chosenCategory: Category? = null,
    val targetPhoto: Photo = testPhoto,
    val purchaseItemList: List<PurchaseItem> = listOf()

)