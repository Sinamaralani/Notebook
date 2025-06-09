package com.example.notebook.addNote.domain.usecases

import com.example.notebook.core.domain.model.NoteItem
import com.example.notebook.core.domain.repository.NoteRepository

class UpsertNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(title: String, description: String, imageUrl: String): Boolean {
        if (title.isEmpty() || description.isEmpty()) {
            return false
        } else {
            val note = NoteItem(
                title = title,
                description = description,
                imageUrl = imageUrl,
                dateAdded = System.currentTimeMillis()
            )
            noteRepository.upsertNote(note)
            return true
        }
    }
}