package com.apexascent.assignment.Events

sealed class ChatEvent {
    data class updatePrompt(val newPrompt: String): ChatEvent()
    data class sendPrompt(val prompt: String): ChatEvent()
    data class InitializeChat(val initialUserPrompt:String, val initialAiResponse:String):ChatEvent()

}