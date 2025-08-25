package com.booktok.notesss.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "notes")
class NoteEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var title: String = ""
    var content: String = ""
    var createdAt: Date = Date()
    var modifiedAt: Date = Date()

    constructor(id: Int, title: String, content: String, createdAt: Date, modifiedAt: Date) {
        this.id = id
        this.title = title
        this.content = content
        this.createdAt = createdAt
        this.modifiedAt = modifiedAt
    }

    constructor(id: Int, title: String, content: String) {
        this.id = id
        this.title = title
        this.content = content
    }
}