package com.booktok.notesss.di

import android.app.Application
import androidx.room.Room
import com.booktok.notesss.data.local_db.data_source.NoteDatabase
import com.booktok.notesss.data.local_db.repository.NoteRepositoryImpl
import com.booktok.notesss.domain.repository.NoteRepository
import com.booktok.notesss.domain.use_case.AddNote
import com.booktok.notesss.domain.use_case.DeleteNote
import com.booktok.notesss.domain.use_case.GetNote
import com.booktok.notesss.domain.use_case.GetNotes
import com.booktok.notesss.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            context = app,
            NoteDatabase::class.java,
            NoteDatabase.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(database: NoteDatabase) : NoteRepository {
        return NoteRepositoryImpl(database.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository) : NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            getNote = GetNote(repository),
            addNote = AddNote(repository)
        )
    }
}