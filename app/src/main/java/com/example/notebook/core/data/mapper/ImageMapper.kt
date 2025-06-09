package com.example.notebook.core.data.mapper

import com.example.notebook.core.data.remote.dto.ImageListDto
import com.example.notebook.core.domain.model.Images

fun ImageListDto.toImages(): Images {
    return Images(
        images = hits?.map { imageDto ->
            imageDto.previewURL ?: ""
        } ?: emptyList()
    )
}