package com.example.notebook.core.data.remote.api

import com.example.notebook.core.data.remote.dto.ImageListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET("/api/")
    suspend fun searchImages(
        @Query("q") query: String,
        @Query("key") apiKey: String = API_KEY,
    ): ImageListDto?


    companion object {
        const val API_KEY = "50743329-aee48a97885132ed567f60a2a"
        const val BASE_URL = "https://pixabay.com"
    }

}