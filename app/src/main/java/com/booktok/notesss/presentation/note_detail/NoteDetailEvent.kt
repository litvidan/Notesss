package com.booktok.notesss.presentation.note_detail

import com.booktok.notesss.domain.model.Note

sealed class NoteDetailEvent {
    data class TitleChanged(val text: String): NoteDetailEvent()
    data class ContentChanged(val text: String): NoteDetailEvent()
    object SaveNote: NoteDetailEvent()
    object DeleteNote: NoteDetailEvent()
}
