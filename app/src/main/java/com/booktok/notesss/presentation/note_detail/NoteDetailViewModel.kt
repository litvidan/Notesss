package com.booktok.notesss.presentation.note_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteDetailViewModel(private val repository: NoteRepository) : ViewModel() {
    private val _noteId = MutableStateFlow<Int?>(null)

    val note: StateFlow<Note?> = _noteId.filterNotNull()
        .flatMapLatest { id ->
            flow {
                emit(repository.getNote(id))
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun loadNote(id: Int) {
        _noteId.value = id
    }

    fun saveNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}