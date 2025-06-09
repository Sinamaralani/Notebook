package com.example.notebook.core.data.repository

import com.example.notebook.core.data.local.NoteDatabase
import com.example.notebook.core.data.mapper.toNoteEntityForDelete
import com.example.notebook.core.data.mapper.toNoteEntityForInsert
import com.example.notebook.core.data.mapper.toNoteItem
import com.example.notebook.core.domain.model.NoteItem
import com.example.notebook.core.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteDb: NoteDatabase) : NoteRepository {
    private val noteDao = noteDb.noteDao

    override suspend fun getAllNotes(): List<NoteItem> {
        return noteDao.getAllNotes().map { it.toNoteItem() }
    }

    override suspend fun upsertNote(note: NoteItem) {
        noteDao.upsertNote(note.toNoteEntityForInsert())
    }

    override suspend fun deleteNote(note: NoteItem) {
        noteDao.deleteNote(note.toNoteEntityForDelete())
    }
}