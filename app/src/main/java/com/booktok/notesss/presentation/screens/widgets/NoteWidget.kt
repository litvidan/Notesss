package com.booktok.notesss.presentation.screens.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteWidget(
    title: String,
    content: String,
    dateTime: LocalDateTime,
    maxContentLength: Int = 250 // Максимальная длина текста превью
) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(5.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = content,
            maxLines = 5, // ограничиваем по вертикали
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Row {
            Text(text = formatDate(dateTime))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoteWidgetPreview(){
    val title = "Note Title"
    val content = "Nuttus set libertine malus, " +
            "ut none aliqua ya tut i ostanus. " +
            "Dullest liver tam malus, " +
            "ut nun a tam uzh ostalus malost"
    val date = LocalDateTime.now()

    NoteWidget(title, content, date)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDate(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
    return dateTime.format(formatter)
}