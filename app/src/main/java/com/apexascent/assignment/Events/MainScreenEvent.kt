package com.apexascent.assignment.Events

import android.media.MediaPlayer
import com.apexascent.assignment.data.TarotCard
import com.apexascent.assignment.database.TarotDao
import com.apexascent.assignment.database.TarotResult

sealed class MainScreenEvent {
    data class UpdateUserQuestion(val newQuestion: String): MainScreenEvent()
    data class SelectTarotCard(val card: TarotCard): MainScreenEvent()
    data class SaveTarotResult(val tarotResult: TarotResult, val dao: TarotDao): MainScreenEvent()
    data class PlayAudio(val mediaPlayer: MediaPlayer): MainScreenEvent()
    data class StopAudio(val mediaPlayer: MediaPlayer): MainScreenEvent()
    data class ContinueChat(val dao: TarotDao): MainScreenEvent()
    object ShuffleCards: MainScreenEvent()
    object ShowDialog: MainScreenEvent()
    data class DismissDialog(val dao: TarotDao): MainScreenEvent()
    object FetchAIResponse: MainScreenEvent()
}