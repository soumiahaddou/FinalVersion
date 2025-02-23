package com.apexascent.assignment.Events

import com.apexascent.assignment.database.TarotDao

sealed class HistoryEvent {
    data class RetrieveHistory(val dao: TarotDao): HistoryEvent()
    data class DeleteHistory(val dao: TarotDao): HistoryEvent()
}