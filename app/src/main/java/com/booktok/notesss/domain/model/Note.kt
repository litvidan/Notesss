package com.booktok.notesss.domain.model

import java.util.Date

data class Note(
    val id: Int?,
    val title: String,
    val content: String,
    val createdAt: Date,
    val modifiedAt: Date
)