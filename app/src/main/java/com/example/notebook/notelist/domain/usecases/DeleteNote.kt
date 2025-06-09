package com.example.notebook.notelist.domain.usecases

import com.example.notebook.core.domain.model.NoteItem
import com.example.notebook.core.domain.repository.NoteRepository

class DeleteNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(noteItem: NoteItem) {
        noteRepository.deleteNote(noteItem)
    }
}