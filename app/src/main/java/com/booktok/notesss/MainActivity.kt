package com.booktok.notesss

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.booktok.notesss.presentation.navigation.Navigation
import com.booktok.notesss.presentation.ui.theme.NotesssTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesssTheme {
                Navigation()
            }
        }
    }
}
