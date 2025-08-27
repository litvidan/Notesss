package com.booktok.notesss.presentation.notes_list

import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.util.NotesOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NotesOrder) : NotesEvent() // TODO("Implement order")
    data class DeleteNote(val note: Note): NotesEvent()
    object RestoreNote: NotesEvent() // TODO("Implement restore")
    object ToggleOrderSection: NotesEvent() // TODO("Same as Order")
}