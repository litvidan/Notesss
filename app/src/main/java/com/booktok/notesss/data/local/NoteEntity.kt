package com.booktok.notesss.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var content: String = "",
    var createdAt: Date = Date(),
    var modifiedAt: Date = Date()
)