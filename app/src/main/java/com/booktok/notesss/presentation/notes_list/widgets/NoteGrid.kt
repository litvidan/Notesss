package com.booktok.notesss.presentation.notes_list.widgets

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.booktok.notesss.R
import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.presentation.ui.theme.NotesssTheme
import java.util.Date

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteGrid(
    notes: List<Note>,
    onInsert: (Long?) -> (Unit) = {}
) {
    if (notes.isEmpty())
        Text(
            text = stringResource(R.string.no_notes),
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground

        )
    else
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // 2 колонки
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            items(notes){ note ->
                NoteWidget(
                    note.title,
                    note.content,
                    note.createdAt,
                    onClick = {
                        onInsert(note.id)
                    })
            }
        }
}

@Preview(name = "Note Grid Light",showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Note Grid Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NoteGridPreview(){
    val date : Date = Date()

    val notes = List(10) { index ->
        Note(
            id = ((index + 1).toLong()),
            title = "Note ${index + 1}",
            content = "This is note number ${index + 1}",
            createdAt = date,
            modifiedAt = date
        )
    }
    NotesssTheme {
        NoteGrid(notes)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun EmptyNoteGridPreviewLight(){
    val notes: List<Note> = listOf()

    NotesssTheme {
        NoteGrid(notes)
    }
}