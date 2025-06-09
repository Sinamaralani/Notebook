package com.example.notebook.core.data.repository

import com.example.notebook.core.domain.model.NoteItem
import com.example.notebook.core.domain.repository.NoteRepository

class FakeNoteRepository : NoteRepository {

    private var noteItems = mutableListOf<NoteItem>()

    fun shouldHaveFilledList(shouldHaveFilledList: Boolean) {
        noteItems =
            if (shouldHaveFilledList) {
                mutableListOf(
                    NoteItem(
                        title = "d title 1",
                        description = "desc 1",
                        imageUrl = "url1",
                        dateAdded = 1
                    ),
                    NoteItem(
                        title = "d title 2",
                        description = "desc 2",
                        imageUrl = "url2",
                        dateAdded = 2
                    ),
                    NoteItem(
                        title = "d title 3",
                        description = "desc 3",
                        imageUrl = "url3",
                        dateAdded = 3
                    ),
                    NoteItem(
                        title = "d title 4",
                        description = "desc 4",
                        imageUrl = "url4",
                        dateAdded = 4
                    ),
                )
            } else {
                mutableListOf()
            }
    }

    override suspend fun upsertNote(noteItem: NoteItem) {
        noteItems.add(noteItem)
    }

    override suspend fun deleteNote(noteItem: NoteItem) {
        noteItems.remove(noteItem)
    }

    override suspend fun getAllNotes(): List<NoteItem> {
        return noteItems
    }
}