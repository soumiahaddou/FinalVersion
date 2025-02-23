package com.apexascent.assignment.AI_integration

import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatResponse {
    const val API_KEY = "Your key here"
    suspend fun getResponse(prompt: String): Chat{
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = API_KEY
        )
        try {
            val response = withContext(Dispatchers.IO){
                generativeModel.generateContent(prompt)
            }
            return Chat(response.text ?: "Error", false)

        }  catch (e : Exception) {
            return Chat(e.message ?: "Error", false)
        }

    }

}