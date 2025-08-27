package com.booktok.notesss.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed class Screen: NavKey {
    @Serializable
    data object NotesList : Screen()

    @Serializable
    data class NoteDetail(val id: Long?) : Screen()
}