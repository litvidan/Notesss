package com.booktok.notesss.data.local_db.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.booktok.notesss.data.local_db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    // Create & Update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: NoteEntity) : Long

    // Read
    @Query("SELECT * FROM notes WHERE id=:id")
    suspend fun getById(id: Long) : NoteEntity?

    @Query("SELECT * FROM notes")
    fun getAll(): Flow<List<NoteEntity>>

    // Delete
    @Delete
    suspend fun delete(note: NoteEntity)
}