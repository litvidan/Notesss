package com.booktok.notesss.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    // Create & Update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity)

    // Read
    @Query("SELECT * FROM notes WHERE id=:id")
    suspend fun getById(id: Int) : NoteEntity?

    @Query("SELECT * FROM notes")
    fun getAll(): Flow<List<NoteEntity>>

    // Delete
    @Delete
    suspend fun delete(note: NoteEntity)
}