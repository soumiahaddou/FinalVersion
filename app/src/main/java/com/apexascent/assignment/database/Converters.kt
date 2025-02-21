package com.apexascent.assignment.database

import androidx.room.TypeConverter
import com.apexascent.assignment.data.TarotCard
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTarotCardList(value: List<TarotCard>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toTarotCardList(value: String): List<TarotCard> {
        val listType = object : TypeToken<List<TarotCard>>() {}.type
        return gson.fromJson(value, listType)
    }
}