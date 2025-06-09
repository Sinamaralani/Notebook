package com.example.notebook.core.data.mapper

import com.example.notebook.core.data.local.NoteEntity
import com.example.notebook.core.domain.model.NoteItem

fun NoteItem.toNoteEntityForInsert(): NoteEntity {
    return NoteEntity(
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded
    )
}

fun NoteItem.toNoteEntityForDelete(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded
    )
}

fun NoteEntity.toNoteItem(): NoteItem {
    return NoteItem(
        id = id ?: 0,
        title = title,
        description = description,
        imageUrl = imageUrl,
        dateAdded = dateAdded
    )
}