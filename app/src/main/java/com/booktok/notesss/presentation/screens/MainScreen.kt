package com.booktok.notesss.presentation.screens

import NoteGrid
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.booktok.notesss.R
import com.booktok.notesss.domain.Note
import com.booktok.notesss.presentation.screens.widgets.TopBar
import java.util.Date
import java.util.GregorianCalendar

@Composable
fun MainScreen() {
    MainScreenView()
}

@Composable
fun MainScreenView(){

    val calendar = GregorianCalendar()
    val date : Date = calendar.getTime()
    val notes = List(10) { index ->
        Note(
            id = (index + 1),
            title = "Note ${index + 1}",
            content = "This is note number ${index + 1}",
            createdAt = date,
            modifiedAt = date
        )
    }

    Box{
        Column{
            TopBar(
                isDarkTheme = false,
                onToggleTheme = {},
                onSearchQueryChange = {}
            )
            NoteGrid(
                notes = notes
            )
        }
        FloatingActionButton(
            onClick = {
                // Add logic
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add_note))
        }
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview(){
    MainScreenView()
}