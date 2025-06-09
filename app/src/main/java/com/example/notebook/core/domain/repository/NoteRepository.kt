package com.example.notebook.core.domain.repository

import com.example.notebook.core.domain.model.NoteItem

interface NoteRepository {

    suspend fun getAllNotes(): List<NoteItem>

    suspend fun upsertNote(note: NoteItem)

    suspend fun deleteNote(note: NoteItem)
}