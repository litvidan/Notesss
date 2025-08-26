package com.booktok.notesss.presentation.screens.widgets

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.booktok.notesss.domain.formatDate
import com.booktok.notesss.presentation.ui.theme.NotesssTheme
import java.util.Date

@Composable
fun NoteWidget(
    title: String,
    content: String,
    dateTime: Date,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(16.dp))
            .padding(10.dp)
            .clickable{onClick()},
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = content,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = formatDate(LocalContext.current, dateTime),
            color = MaterialTheme.colorScheme.onSecondary
        )

    }
}


@Preview(name = "Light mode",showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark mode", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NoteWidgetPreviewDark() {
    val title = "Note Title"
    val content = "Nuttus set libertine malus, " +
            "ut none aliqua ya tut i ostanus. " +
            "Dullest liver tam malus, " +
            "ut nun a tam uzh ostalus malost" +
            " lorem ipsum dolor et cetera" +
            " overflow examplus shiftus" +
            " labus codus biggus dickus" +
            " romani domus aurelius hahahahaha"
    val date = Date()

    NotesssTheme {
        NoteWidget(title, content, date)
    }
}

