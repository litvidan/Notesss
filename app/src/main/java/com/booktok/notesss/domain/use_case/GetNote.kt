package com.booktok.notesss.domain.use_case

import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Long): Note?{
        return repository.getNote(id)
    }
}