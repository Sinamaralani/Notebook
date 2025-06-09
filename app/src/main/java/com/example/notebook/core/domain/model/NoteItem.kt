package com.example.notebook.core.domain.model

data class NoteItem(
    val id: Int = 0,
    val title: String,
    val description: String,
    val imageUrl: String,
    val dateAdded: Long
)