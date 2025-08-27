package com.booktok.notesss.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}