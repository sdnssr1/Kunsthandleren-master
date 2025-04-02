package com.example.kunsthandleren

data class ArtPurchaseUiState (
    val price: Float = 0f,

    val chosenFilter: Filters? = null,
    val chosenArtist: Artist? = null,
    val chosenCategory: Category? = null,
    val targetPhoto: Photo = testPhoto,
    val purchaseItemList: List<PurchaseItem> = listOf()

)
