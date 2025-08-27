package com.booktok.notesss.presentation.notes_list

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.use_case.NoteUseCases
import com.booktok.notesss.domain.util.NotesOrder
import com.booktok.notesss.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel(){
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var lastDeletedNote: Note? = null

    private var getNotesJob: Job? = null

    init{
        getNotes(NotesOrder.ByCreationDate(OrderType.Descending))
    }

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.Order -> {
                if(state.value.notesOrder::class == event.noteOrder::class &&
                    state.value.notesOrder.orderType == event.noteOrder.orderType
                ){
                    return
                }

            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    lastDeletedNote = event.note
                    noteUseCases.deleteNote(event.note)
                }
            }
            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(lastDeletedNote ?: return@launch)
                    lastDeletedNote = null
                }
            }
            is NotesEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(isOrderSectionVisible = !state.value.isOrderSectionVisible)
            }
        }
    }


    private fun getNotes(notesOrder: NotesOrder) {
        getNotesJob?.cancel()

        getNotesJob = noteUseCases.getNotes(notesOrder)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes =  notes,
                    notesOrder = notesOrder
                )
            }
            .launchIn(viewModelScope)
    }

}