package com.booktok.notesss.presentation.note_detail

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.booktok.notesss.R
import com.booktok.notesss.domain.model.Note
import com.booktok.notesss.domain.repository.NoteRepository
import com.booktok.notesss.presentation.util.formatDate
import com.booktok.notesss.presentation.util.formatTime
import com.booktok.notesss.presentation.note_detail.widgets.NotesssTextField
import com.booktok.notesss.presentation.notes_list.NotesListViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.booktok.notesss.data.mock.NoteRepositoryMockImpl


@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun NoteDetailScreen(
    noteRepository: NoteRepository,
    noteId: Int? = null,
    onBack: () -> Unit
){
    val noteDetailViewModel = NoteDetailViewModel(noteRepository, noteId)
    val note = noteDetailViewModel.note.collectAsState().value

    NoteDetailScreenView(
        note = note,
        onBack = { note ->
            noteDetailViewModel.saveNote(note)
            onBack()
        },
        onDelete = { note ->
            noteDetailViewModel.deleteNote(note)
            onBack()
        }
    )
}

@Composable
fun NoteDetailScreenView(
    note: Note,
    onBack: (Note) -> Unit,
    onDelete: (Note) -> Unit
){

    var title by remember { mutableStateOf(note.title) }
    var content by remember { mutableStateOf(note.content) }
    val onTitleChange : (String) -> Unit = { newTitle ->
        title = newTitle.replace("\n", "")
        note.title = newTitle.replace("\n", "")
    }
    val onContentChange : (String) -> Unit = { newContent ->
        content = newContent
        note.content = newContent
    }


    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = {onBack(note)}) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back_to_list))
            }

            IconButton(onClick = {onDelete(note)}) {
                Icon(Icons.Default.Delete, contentDescription = stringResource(R.string.delete_note))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        NotesssTextField(
            value = title,
            onValueChange = onTitleChange,
            placeholder = stringResource(R.string.title),
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            textStyle = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        NotesssTextField(
            value = content,
            onValueChange = onContentChange,
            placeholder = stringResource(R.string.content),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp),
            textStyle = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        note.createdAt.let {
            Text(
                text = "${stringResource(R.string.created_at)}: ${formatDate(LocalContext.current, it)} ${formatTime(it)}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
        note.modifiedAt?.let {
            Text(text = "${stringResource(R.string.modified_at)}: ${formatDate(LocalContext.current, it)} ${formatTime(it)}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailScreenPreview(){
    val noteRepository = NoteRepositoryMockImpl()
    val noteId = 1


    MaterialTheme {
        NoteDetailScreen(
            noteRepository = noteRepository,
            noteId = noteId,
            onBack = { }
        )
    }
}

