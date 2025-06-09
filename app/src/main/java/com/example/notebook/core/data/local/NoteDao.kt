package com.example.notebook.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(noteEntity: NoteEntity)

    @Query("SELECT * FROM noteEntity")
    suspend fun getAllNotes(): List<NoteEntity>

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)

}