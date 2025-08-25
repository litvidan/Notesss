package com.booktok.notesss.data.repository

import com.booktok.notesss.data.local.NoteDao
import com.booktok.notesss.domain.Note
import com.booktok.notesss.domain.NoteRepository
import com.booktok.notesss.domain.toDomain
import com.booktok.notesss.domain.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(private val dao: NoteDao) : NoteRepository {
    override suspend fun insertNote(note: Note) {
        dao.insert(note.toEntity())
    }

    override suspend fun getNote(id: Int) : Note? {
        return dao.getById(id)?.toDomain()
    }

    override suspend fun deleteNote(note: Note) {
        dao.delete(note.toEntity())
    }

    override fun getNotes(): Flow<List<Note>> = dao.getAll().map { entities -> entities.map { it.toDomain() } }
}