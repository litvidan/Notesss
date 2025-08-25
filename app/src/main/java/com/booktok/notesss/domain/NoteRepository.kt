package com.booktok.notesss.domain

import kotlinx.coroutines.flow.Flow

// This is for CRUD operations
interface NoteRepository{
    suspend fun insertNote(note: Note) // Create & Update
    suspend fun getNote(id: Int) : Note?// Read
    suspend fun deleteNote(note: Note) // Delete
    fun getNotes() : Flow<List<Note>>
}