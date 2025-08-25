package com.booktok.notesss.presentation.screens.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Date
import com.booktok.notesss.domain.formatDate

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
            .background(Color.LightGray, RoundedCornerShape(16.dp))
            .padding(10.dp),
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
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Row {
            Text(text = formatDate(LocalContext.current, dateTime))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteWidgetPreview() {
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

    NoteWidget(title, content, date)
}
