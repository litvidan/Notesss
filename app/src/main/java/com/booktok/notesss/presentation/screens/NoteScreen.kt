package com.booktok.notesss.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.booktok.notesss.R
import com.booktok.notesss.domain.Note
import com.booktok.notesss.domain.formatDate
import com.booktok.notesss.domain.formatTime
import com.booktok.notesss.presentation.screens.widgets.NotesssTextField
import java.util.Date


@Composable
fun NoteScreen(){
    val note = Note(1,"asdf", "asdf", Date(), Date())
    NoteScreenView()
}

@Composable
fun NoteScreenView(note: Note? = null){
    val onBack = {}
    val onDelete = {}
    val onTitleChange : (String) -> Unit = {}
    val onContentChange : (String) -> Unit = {}


    Column(modifier = Modifier
        .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back_to_list))
            }

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete_note))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        NotesssTextField(
            value = "",
            onValueChange = onTitleChange,
            placeholder = stringResource(R.string.title),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        NotesssTextField(
            value = "",
            onValueChange = onContentChange,
            placeholder = stringResource(R.string.content),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        note?.createdAt?.let {
            Text(
                text = "${stringResource(R.string.created_at)}: ${formatDate(LocalContext.current, it)} ${formatTime(it)}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
        note?.modifiedAt?.let {
            Text(text = "${stringResource(R.string.modified_at)}: ${formatDate(LocalContext.current, it)} ${formatTime(it)}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview(){
    MaterialTheme {
        NoteScreen()
    }
}

