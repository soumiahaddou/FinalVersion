package com.apexascent.assignment.States

import com.apexascent.assignment.database.TarotResult

data class HistoryState(
    val historyList: List<TarotResult> = mutableListOf()
)