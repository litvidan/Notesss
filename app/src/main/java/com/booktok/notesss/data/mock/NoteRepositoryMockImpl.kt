package com.booktok.notesss.data.mock

import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.Date

class NoteRepositoryMockImpl: NoteRepository {
    val notes = mutableListOf<Note>(
        Note(
            id = 1,
            title = "About doctor",
            content = "A man was hospitalized with 6 plastic horses up his ass," +
                    " the doctor described his condition as \"stable\"\n",
            createdAt = Date(),
            modifiedAt = Date()
        ),
        Note(
            id = 2,
            title = "Про TCP и UDP",
            content = "Я бы пошутил про UDP, но боюсь до вас не дойдёт.\n" +
                    "А если не дойдёт шутка про TCP, то я её ещё раз повторю",
            createdAt = Date(),
            modifiedAt = Date()
        ),
        Note(
            id = 3,
            title = "Про TCP и UDP",
            content = "Я бы пошутил про UDP, но боюсь до вас не дойдёт.\n" +
                    "А если не дойдёт шутка про TCP, то я её ещё раз повторю",
            createdAt = Date(),
            modifiedAt = Date()
        ),
        Note(
            id = 4,
            title = "",
            content = "Работа, работа, перейди на чат-бота",
            createdAt = Date(),
            modifiedAt = Date()
        ),
    )

    override suspend fun insertNote(note: Note) {
        if(note.title == "" && note.content == "") return

        val index = notes.indexOfFirst { it.id == note.id }
        if (index >= 0) {
            notes[index] = note.copy(modifiedAt = Date())
        } else {
            notes.add(note)
        }
    }

    override suspend fun getNote(id: Int): Note? {
        return notes.firstOrNull{it.id == id}
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }

    override fun getNotes(): Flow<List<Note>> {
        return flowOf(notes)
    }
}