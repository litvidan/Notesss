package com.booktok.notesss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.booktok.notesss.data.local_db.repository.NoteRepositoryImpl
import com.booktok.notesss.data.mock.NoteRepositoryMockImpl
import com.booktok.notesss.presentation.navigation.Navigation
import com.booktok.notesss.presentation.ui.theme.NotesssTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val noteRepository = NoteRepositoryMockImpl()

        enableEdgeToEdge()
        setContent {
            NotesssTheme {
                Navigation(noteRepository)
            }
        }
    }
}
