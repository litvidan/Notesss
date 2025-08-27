package com.booktok.notesss.presentation.note_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date

class NoteDetailViewModel(
    private val repository: NoteRepository,
    noteId: Int?
) : ViewModel() {
    private val _note = MutableStateFlow(
        Note(
            id = 0,
            title = "",
            content = "",
            createdAt = Date(),
            modifiedAt = null
        )
    )
    val note: StateFlow<Note> = _note



    init {
        viewModelScope.launch {
            val id = if (noteId == null) {
                getUniqueId()
            } else {
                noteId
            }

            if (noteId == null) {
                _note.value = Note(id, "", "", Date(), null)
            } else {
                val loadedNote = repository.getNote(id)
                if (loadedNote != null) {
                    _note.value = loadedNote
                }
            }
        }
    }

    private suspend fun getUniqueId(): Int {
        val notes = repository.getNotes().first()
        val existingIds = notes.map { it.id }.toSet()
        var newId = 1
        while (existingIds.contains(newId)) {
            newId++
        }
        return newId
    }

    fun saveNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}
