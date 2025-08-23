package com.booktok.notesss.presentation.screens

import NoteGrid
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.booktok.notesss.domain.Note
import com.booktok.notesss.presentation.screens.widgets.TopBar
import java.util.Calendar
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
            id = (index + 1).toString(),
            title = "Note ${index + 1}",
            content = "This is note number ${index + 1}",
            createdAt = date,
            modifiedAt = date
        )
    }

    Column{
        TopBar()
        NoteGrid(
            notes = notes
        )
    }


}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview(){
    MainScreenView()
}