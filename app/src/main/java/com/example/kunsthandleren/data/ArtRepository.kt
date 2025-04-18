package com.example.kunsthandleren.data

import com.example.kunsthandleren.network.RetrofitInstance
import com.example.kunsthandleren.network.dto.*

class ArtRepository {

    suspend fun getArtists(): List<ArtistDto> =
        RetrofitInstance.api.getArtists()

    suspend fun getCategories(): List<CategoryDto> =
        RetrofitInstance.api.getCategories()

    suspend fun getPhotos(): List<PhotoDto> =
        RetrofitInstance.api.getPhotos()

    suspend fun getFrameTypes(): List<FrameTypeDto> =
        RetrofitInstance.api.getFrameTypes()

    suspend fun getPhotoSizes(): List<PhotoSizeDto> =
        RetrofitInstance.api.getPhotoSizes()

}

