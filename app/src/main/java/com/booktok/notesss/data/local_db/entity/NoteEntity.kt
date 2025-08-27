package com.booktok.notesss.data.local_db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var title: String,
    var content: String,
    var createdAt: Date,
    var modifiedAt: Date
)