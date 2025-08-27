package com.booktok.notesss.domain.util

sealed class NotesOrder(val orderType: OrderType) {
    class ByTitle(orderType: OrderType) : NotesOrder(orderType)
    class ByCreationDate(orderType: OrderType) : NotesOrder(orderType)
    class ByModificationDate(orderType: OrderType) : NotesOrder(orderType)
    class BySearchQuery(query: String?, orderType: OrderType) : NotesOrder(orderType)
}