package com.booktok.notesss.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.booktok.notesss.presentation.note_detail.NoteDetailScreen
import com.booktok.notesss.presentation.notes_list.NotesListScreen
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import com.booktok.notesss.domain.repository.NoteRepository

@Composable
fun Navigation(
    noteRepository: NoteRepository
){
    val backStack = rememberNavBackStack<Screen>(Screen.NotesList)

    NavDisplay(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = entryProvider {
            entry<Screen.NotesList>{
                NotesListScreen(
                    onInsert = { id ->
                        backStack.add(Screen.NoteDetail(id))
                    }
                )
            }
            entry<Screen.NoteDetail>{ key ->
                NoteDetailScreen(
                    onBack = { backStack.removeLastOrNull()},
                    noteId = key.id
                )
            }
        }
    )
}
