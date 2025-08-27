package com.booktok.notesss.domain.use_case

import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.repository.NoteRepository
import com.booktok.notesss.domain.util.NotesOrder
import com.booktok.notesss.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
        notesOrder: NotesOrder = NotesOrder.ByCreationDate(OrderType.Descending),
        searchQuery: String? = null
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when(notesOrder.orderType){
                is OrderType.Ascending -> {
                    when(notesOrder){
                        is NotesOrder.ByTitle -> notes.sortedBy { it.title.lowercase() }
                        is NotesOrder.ByCreationDate -> notes.sortedBy { it.createdAt.time }
                        is NotesOrder.ByModificationDate -> notes.sortedBy { it.modifiedAt?.time }
                        is NotesOrder.BySearchQuery -> {
                            //TODO("Implement search ability")
                            notes
                        }
                    }
                }

                is OrderType.Descending -> {
                    when(notesOrder){
                        is NotesOrder.ByTitle -> notes.sortedByDescending { it.title.lowercase() }
                        is NotesOrder.ByCreationDate -> notes.sortedByDescending { it.createdAt.time }
                        is NotesOrder.ByModificationDate -> notes.sortedByDescending { it.modifiedAt?.time }
                        is NotesOrder.BySearchQuery -> {
                            //TODO("Implement search ability")
                            notes
                        }
                    }
                }
            }
        }
    }

}