package com.apexascent.assignment.States

import com.apexascent.assignment.data.TarotCard
import com.apexascent.assignment.util.Constants

data class MainScreenState(
    val userQuestion: String = "",
    val tarotDeck: List<TarotCard> = Constants.tarotDeck,
    val selectedCards: List<TarotCard> = emptyList(),
    val isShuffling: Boolean = false,
    val isLoading: Boolean = false,
    val showDialog: Boolean = false,
    val aiResponse: String = "",
    val userPrompt:String = "",
    val isPlaying: Boolean= false

)
