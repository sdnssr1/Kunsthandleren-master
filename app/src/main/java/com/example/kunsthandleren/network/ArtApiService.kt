package com.example.kunsthandleren.network

import com.example.kunsthandleren.network.dto.*
import retrofit2.http.GET

interface ArtApiService {
    @GET("categories")
    suspend fun getCategories(): List<CategoryDto>

    @GET("artists")
    suspend fun getArtists(): List<ArtistDto>

    @GET("photos")
    suspend fun getPhotos(): List<PhotoDto>

    @GET("frametypes")
    suspend fun getFrameTypes(): List<FrameTypeDto>

    @GET("photosizes")
    suspend fun getPhotoSizes(): List<PhotoSizeDto>
}

