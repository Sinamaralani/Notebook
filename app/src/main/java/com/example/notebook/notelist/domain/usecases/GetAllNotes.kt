package com.example.notebook.notelist.domain.usecases

import com.example.notebook.core.domain.model.NoteItem
import com.example.notebook.core.domain.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(isOrderByTitle: Boolean): List<NoteItem> {
        return if (isOrderByTitle) {
            noteRepository.getAllNotes().sortedBy { it.title.lowercase() }
        } else {
            noteRepository.getAllNotes().sortedBy { it.dateAdded }
        }
    }

}