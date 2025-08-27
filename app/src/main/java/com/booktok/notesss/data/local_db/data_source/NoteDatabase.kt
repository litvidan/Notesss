package com.booktok.notesss.data.local_db.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.booktok.notesss.data.local_db.entity.NoteEntity
import com.booktok.notesss.data.local_db.util.Converters

@Database(entities = [NoteEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao
}