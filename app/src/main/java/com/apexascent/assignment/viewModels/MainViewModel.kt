package com.apexascent.assignment.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apexascent.assignment.AI_integration.ChatResponse
import com.apexascent.assignment.Events.MainScreenEvent
import com.apexascent.assignment.States.MainScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainViewModel:ViewModel() {

    var _mainState = MutableStateFlow(MainScreenState())
    private set
    val mainState = _mainState.asStateFlow()

    fun onEvent(event: MainScreenEvent){
        when(event){
            MainScreenEvent.DismissDialog -> {
                _mainState.update {
                    it.copy(
                        showDialog = false
                    )
                }
            }
            MainScreenEvent.FetchAIResponse -> {
                _mainState.update {
                    it.copy( isLoading = true)
                }
                viewModelScope.launch {
                    val cardNames = _mainState.value.selectedCards.joinToString(",") { it.cardName }
                    val cardMeanings = _mainState.value.selectedCards.joinToString(",") { it.cardMeaning.joinToString(",")}
                    val prompt = "${_mainState.value.userQuestion} based on my tarot cards: $cardNames, and their meanings: $cardMeanings"
                    val aiResponse = ChatResponse.getResponse(prompt).prompt
                    _mainState.update {
                        it.copy(
                            aiResponse = aiResponse,
                            userPrompt = prompt,
                            isLoading = false
                        )
                    }
                }
            }
            MainScreenEvent.ShowDialog -> {
                _mainState.update {
                    it.copy(
                        showDialog = true
                    )
                }
            }
            MainScreenEvent.ShuffleCards -> {
                viewModelScope.launch {

                    repeat(5){
                        _mainState.update {
                            it.copy(
                                isShuffling = true
                            )
                        }
                        var tarotDeck = _mainState.value.tarotDeck
                        tarotDeck = tarotDeck.shuffled()
                        delay(500) // Delay between shuffles
                        _mainState.update {
                            it.copy(
                                tarotDeck = tarotDeck,
                                isShuffling = false
                            )
                        }
                    }


                }
            }
            is MainScreenEvent.UpdateUserQuestion -> {
                _mainState.update {
                    it.copy(
                        userQuestion = event.newQuestion
                    )
                }
            }
            is MainScreenEvent.SelectTarotCard -> {
                val selectedCards = _mainState.value.selectedCards.toMutableList()
                if (selectedCards.contains(event.card)){
                    selectedCards.remove(event.card)
                }
                else if(selectedCards.size < 3 ){
                    selectedCards.add(event.card)
                }
                _mainState.update {
                    it.copy(
                        selectedCards = selectedCards
                    )
                }
            }

            is MainScreenEvent.SaveTarotResult ->
            {
                viewModelScope.launch {  event.dao.saveResult(event.tarotResult)  }

            }

            is MainScreenEvent.PlayAudio -> {

                event.mediaPlayer.start()
                _mainState.update {
                    it.copy(
                        isPlaying = true
                    )
                }
            }

            is MainScreenEvent.StopAudio -> {
                event.mediaPlayer.pause()
                _mainState.update {
                    it.copy(
                        isPlaying = false
                    )
                }
            }
        }
    }
    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return dateFormat.format(Date())
    }

}
