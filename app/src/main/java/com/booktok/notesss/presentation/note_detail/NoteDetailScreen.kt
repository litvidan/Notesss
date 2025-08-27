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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.booktok.notesss.R
import com.booktok.notesss.presentation.note_detail.widgets.NotesssTextField
import com.booktok.notesss.presentation.util.formatDate
import com.booktok.notesss.presentation.util.formatTime


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteDetailScreen(
    viewModel: NoteDetailViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
    noteId: Long? = null
){
    LaunchedEffect(noteId) {
        viewModel.loadNoteId(noteId)
    }

    val title = viewModel.title.value
    val content = viewModel.content.value
    val createdAt = viewModel.createdAt
    val modifiedAt = viewModel.modifiedAt

    val onContentChange : (String) -> Unit = { text ->
        viewModel.onEvent(NoteDetailEvent.ContentChanged(text))
    }

    val onTitleChange : (String) -> Unit =  {text ->
        viewModel.onEvent(NoteDetailEvent.TitleChanged(text))
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(NoteDetailEvent.SaveNote)
                }
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = stringResource(R.string.save_note))
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_to_list)
                    )
                }

                IconButton(onClick = {
                    viewModel.onEvent(NoteDetailEvent.DeleteNote)
                    onBack()
                }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_note)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            NotesssTextField(
                value = title.text,
                onValueChange = onTitleChange,
                placeholder = stringResource(R.string.title),
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                textStyle = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            NotesssTextField(
                value = content.text,
                onValueChange = onContentChange,
                placeholder = stringResource(R.string.content),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
                textStyle = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            createdAt?.let {
                Text(
                    text = "${stringResource(R.string.created_at)}: ${
                        formatDate(
                            LocalContext.current,
                            it
                        )
                    } ${formatTime(it)}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
            modifiedAt?.let {
                Text(
                    text = "${stringResource(R.string.modified_at)}: ${
                        formatDate(
                            LocalContext.current,
                            it
                        )
                    } ${formatTime(it)}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        }
    }

}

@Preview
@Composable
fun NoteDetailScreenPreview(){
    MaterialTheme {
        NoteDetailScreen()
    }
}

