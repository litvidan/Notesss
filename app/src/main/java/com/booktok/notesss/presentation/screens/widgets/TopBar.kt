package com.booktok.notesss.presentation.screens.widgets

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.booktok.notesss.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onSearchQueryChange: (String) -> Unit
) {
    var isSearchActive by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    TopAppBar(
        title = {
            Crossfade(targetState = isSearchActive) { active ->
                if (active) {
                    SearchTextField(
                        query = searchQuery,
                        onQueryChange = {
                            searchQuery = it
                            onSearchQueryChange(it)
                        },
                        onClose = {
                            searchQuery = ""
                            onSearchQueryChange("")
                            isSearchActive = false
                        }
                    )
                } else {
                    Text(text = stringResource(R.string.app_name))
                }
            }
        },
        actions = {
            if (!isSearchActive) {
                IconButton(onClick = { isSearchActive = true }) {
                    Icon(Icons.Filled.Search, contentDescription = stringResource(R.string.search))
                }
            }
            IconButton(onClick = onToggleTheme) {
                Icon(
                    imageVector = if (isDarkTheme) Icons.Filled.Star else Icons.Filled.Close,
                    contentDescription = stringResource(R.string.switch_theme)
                )
            }
        }
    )
}


@Preview
@Composable
fun TopBarPreview(){
    TopBar(
        isDarkTheme = false,
        onToggleTheme = {},
        onSearchQueryChange = {}
    )
}

@Composable
fun SearchTextField(
    query: String,
    onQueryChange: (String) -> Unit,
    onClose: () -> Unit
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text(stringResource(R.string.search_placeholder)) },
        singleLine = true,
        modifier = Modifier.background(Color.Black, RoundedCornerShape(5.dp)).fillMaxWidth(),
        trailingIcon = {
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = "Закрыть поиск")
            }
        }
    )
}