package com.booktok.notesss.domain.use_case

import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}