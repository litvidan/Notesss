package com.booktok.notesss.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.booktok.notesss.presentation.screens.NoteDetailScreen
import com.booktok.notesss.presentation.screens.NotesListScreen

@Composable
fun Navigation(){
    val backStack = rememberNavBackStack<Screen>(Screen.NotesList)

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
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
                NoteDetailScreen(key.id){
                    backStack.removeLastOrNull()
                }
            }
        }
    )
}
