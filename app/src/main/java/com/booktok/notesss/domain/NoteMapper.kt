package com.booktok.notesss.domain

import com.booktok.notesss.data.local.NoteEntity

fun NoteEntity.toDomain(): Note = Note(id, title, content, createdAt, modifiedAt)
fun Note.toEntity(): NoteEntity = NoteEntity(id, title, content, createdAt, modifiedAt)