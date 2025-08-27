package com.booktok.notesss.presentation.note_detail

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.booktok.notesss.domain.use_case.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewModelScope
import com.booktok.notesss.R
import com.booktok.notesss.domain.model.Note
import dagger.assisted.Assisted
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
): ViewModel(){
    private val _noteId = MutableStateFlow<Long?>(null)
    private val _title = mutableStateOf(NoteTextFieldState(
        placeholder = "Title here"
    ))
    val title: State<NoteTextFieldState>  = _title

    private val _content = mutableStateOf(NoteTextFieldState(
        placeholder = "Type note here"
    ))
    val content: State<NoteTextFieldState> = _content

    var createdAt: Date? = null
    var modifiedAt: Date? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: NoteDetailEvent){
        when(event){
            is NoteDetailEvent.TitleChanged -> {
                _title.value = title.value.copy(
                    text = event.text
                )
            }
            is NoteDetailEvent.ContentChanged -> {
                _content.value = content.value.copy(
                    text = event.text
                )
            }

            is NoteDetailEvent.SaveNote -> {
                viewModelScope.launch {
                    _noteId.value = noteUseCases.addNote(Note(
                        id = _noteId.value,
                        title = title.value.text,
                        content = content.value.text,
                        createdAt = Date(),
                        modifiedAt = null // TODO("Implement logic of this later")
                    ))
                    _eventFlow.emit(UiEvent.SaveNote)
                }
            }

            NoteDetailEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(Note(
                        id = _noteId.value,
                        title = title.value.text, // This is, actually, useless, cause Dao deletes rows by id
                        content = content.value.text,
                        createdAt = Date(),
                        modifiedAt = Date()
                    ))
                }
            }
        }
    }

    sealed class UiEvent{
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveNote : UiEvent()
    }

    fun loadNoteId(id: Long?){
        _noteId.value = id

        _title.value = title.value.copy(
            text = ""
        )
        _content.value = content.value.copy(
            text = ""
        )

        _noteId.value?.let {
            viewModelScope.launch {
                noteUseCases.getNote(_noteId.value!!)?.also { note ->
                    _title.value = title.value.copy(
                        text = note.title
                    )
                    _content.value = content.value.copy(
                        text = note.content
                    )
                    createdAt = note.createdAt
                    modifiedAt = note.modifiedAt
                }
            }
        }
    }
}