package com.booktok.notesss.domain.use_case

data class NoteUseCases (
    val getNotes : GetNotes,
    val deleteNote: DeleteNote,
    val getNote: GetNote,
    val addNote: AddNote
)