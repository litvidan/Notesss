package com.booktok.notesss.domain

import com.booktok.notesss.data.local_db.entity.NoteEntity
import com.booktok.notesss.domain.model.Note

fun NoteEntity.toDomain(): Note = Note(id, title, content, createdAt, modifiedAt)
fun Note.toEntity(): NoteEntity = NoteEntity(id, title, content, createdAt, modifiedAt)