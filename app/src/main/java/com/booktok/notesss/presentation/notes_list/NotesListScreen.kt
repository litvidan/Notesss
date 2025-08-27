package com.booktok.notesss.presentation.notes_list

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.booktok.notesss.R
import com.booktok.notesss.data.mock.NoteRepositoryMockImpl
import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.repository.NoteRepository
import com.booktok.notesss.presentation.notes_list.NotesEvent
import com.booktok.notesss.presentation.notes_list.widgets.NoteGrid
import com.booktok.notesss.presentation.notes_list.widgets.TopBar
import com.booktok.notesss.presentation.ui.theme.NotesssTheme

@SuppressLint("ViewModelConstructorInComposable", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesListScreen(
    viewModel: NotesViewModel = hiltViewModel(),
    onInsert: (Long?) -> Unit
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onInsert(null)
                },
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_note))
            }
        }
    ){
        Column(modifier = Modifier.fillMaxSize()){
            TopBar(
                isDarkTheme = false,
                onToggleTheme = {  },
                onSearchQueryChange = {}
            )
            NoteGrid(
                notes = state.notes,
                onInsert = onInsert
            )
        }
    }

}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(name = "Notes List Dark", showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(name = "Notes List Light", showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun NotesListScreenPreview(){
    NotesssTheme {
        NotesListScreen(
            onInsert = {}
        )
    }

}