package com.example.notebook.core.domain.repository

import com.example.notebook.core.domain.model.Images

interface ImagesRepository {

    suspend fun searchImages(query: String): Images?
}