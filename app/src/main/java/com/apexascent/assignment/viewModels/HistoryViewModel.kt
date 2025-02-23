package com.apexascent.assignment.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apexascent.assignment.Events.HistoryEvent
import com.apexascent.assignment.States.HistoryState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {

    var _state = MutableStateFlow(HistoryState())
        private set
    val state = _state.asStateFlow()

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.RetrieveHistory -> {
                viewModelScope.launch {
                    var historyList = _state.value.historyList.toMutableList()

                    historyList = event.dao.getAllResults().toMutableList()
                    _state.update {
                        it.copy(
                            historyList = historyList
                        )
                    }

                }

            }

            is HistoryEvent.DeleteHistory -> {
                viewModelScope.launch {
                    event.dao.clearAll()
                    _state.update {
                        it.copy(
                            historyList = emptyList()
                        )
                    }
                }

            }
        }
    }
}
