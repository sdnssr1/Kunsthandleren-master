package com.example.kunsthandleren.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kunsthandleren.Artist
import com.example.kunsthandleren.Category
import com.example.kunsthandleren.Filters
import com.example.kunsthandleren.Photo
import com.example.kunsthandleren.PurchaseItem
import com.example.kunsthandleren.data.ArtPurchaseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArtVendorViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ArtPurchaseUiState())
    val uiState: StateFlow<ArtPurchaseUiState> = _uiState.asStateFlow()

    fun updatePurchaseItemList(purchaseItem: PurchaseItem) {
        _uiState.update {
            currentState ->
            val purchaseItemList = currentState.purchaseItemList.toMutableList()
            purchaseItemList.add(purchaseItem)
            currentState.copy(purchaseItemList = purchaseItemList)
        }
    }

    fun updateChosenFilter(filter: Filters){
        _uiState.update {
            currentState ->
            currentState.copy(
                chosenFilter = filter
            )
        }
    }

    fun resetArtistAndCategory(){
        _uiState.update {
            currentState ->
            currentState.copy(
                chosenArtist = null,
                chosenCategory =  null
            )
        }
    }

    fun <T> updateChosenArtistOrCategory(chosen: T) {
        if(uiState.value.chosenCategory != null || uiState.value.chosenArtist != null) {
            resetArtistAndCategory()
        }

        if(chosen is Artist){
            _uiState.update {
                    currentState ->
                    currentState.copy(chosenArtist = chosen)
               }
        } else if (chosen is Category){
            _uiState.update {
                currentState ->
                currentState.copy(chosenCategory = chosen)
            }
        }
    }

    fun setTargetPhoto(photo: Photo){
        _uiState.update {
                currentState ->
            currentState.copy(
                targetPhoto = photo
            )
        }
    }

}