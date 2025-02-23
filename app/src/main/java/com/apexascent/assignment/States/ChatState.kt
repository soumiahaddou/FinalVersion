package com.apexascent.assignment.States

import com.apexascent.assignment.AI_integration.Chat

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val isLoading : Boolean = false

)
