package com.booktok.notesss.domain.use_case

import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note): Long{
        if(note.title != "" || note.content != "") return repository.insertNote(note)
        else return -1
    }
}