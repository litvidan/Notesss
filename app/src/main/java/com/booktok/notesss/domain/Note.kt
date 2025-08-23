package com.booktok.notesss.domain

import java.util.Date

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val createdAt: Date,
    val modifiedAt: Date
)
