package com.booktok.notesss.domain.model

import java.util.Date

data class Note(
    var id: Long? = null,
    var title: String,
    var content: String,
    var createdAt: Date,
    var modifiedAt: Date?
)