package com.booktok.notesss.domain.use_case

import com.booktok.notesss.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int){
        repository.getNote(id)
    }
}