package com.example.notebook.core.data.repository

import com.example.notebook.core.data.mapper.toImages
import com.example.notebook.core.data.remote.api.ImageApi
import com.example.notebook.core.domain.model.Images
import com.example.notebook.core.domain.repository.ImagesRepository
import javax.inject.Inject

class ImagesRepositoryImpl @Inject constructor(private val imageApi: ImageApi) : ImagesRepository {

    override suspend fun searchImages(query: String): Images? {
        return imageApi.searchImages(query)?.toImages()
    }

}