package com.booktok.notesss.presentation.notes_list

import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.util.NotesOrder
import com.booktok.notesss.domain.util.OrderType

data class NotesState(
    val notes: List<Note> = emptyList<Note>(),
    val notesOrder: NotesOrder = NotesOrder.ByCreationDate(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
