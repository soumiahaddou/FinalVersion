package com.apexascent.assignment.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apexascent.assignment.AI_integration.Chat
import com.apexascent.assignment.AI_integration.ChatResponse
import com.apexascent.assignment.Events.ChatEvent
import com.apexascent.assignment.States.ChatState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    private var _chatState = MutableStateFlow(ChatState())
        private set
    val chatState = _chatState.asStateFlow()
    fun onEvent(event: ChatEvent){
        when(event){
            is ChatEvent.sendPrompt -> {
                if (event.prompt.isNotEmpty()){
                    addPrompt(event.prompt)
                    getResponse(event.prompt)

                }

            }
            is ChatEvent.updatePrompt -> {
                _chatState.update {
                    it.copy(prompt = event.newPrompt)
                }
            }

            is ChatEvent.InitializeChat -> {
                _chatState.update {
                    it.copy(
                        chatList = listOf(
                            Chat(event.initialAiResponse, false), // AI response
                            Chat(event.initialUserPrompt, true)  // User prompt
                        ).toMutableList(),
                        isLoading = false
                    )
                }
            }
        }
    }
    private fun addPrompt(prompt:String){
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, Chat(prompt, true))
                },
                prompt = "",
                isLoading = true

            )
        }
    }
    private fun getResponse(prompt:String){
        viewModelScope.launch {
            val chat = ChatResponse.getResponse(prompt)
            _chatState.update {
                it.copy(
                    chatList = it.chatList.toMutableList().apply {
                        add(0, chat)
                    },
                    isLoading = false
                )
            }
        }

    }


}