package com.booktok.notesss.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.booktok.notesss.presentation.screens.widgets.NoteWidget
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    MainScreenView()
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreenView(){
    val title = "Note Title"
    val content = "Nuttus set libertine malus, " +
            "ut none aliqua ya tut i ostanus. " +
            "Dullest liver tam malus, " +
            "ut nun a tam uzh ostalus malost"
    val date = LocalDateTime.now()


    NoteWidget(title, content, date)
}