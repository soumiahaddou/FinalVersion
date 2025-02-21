package com.apexascent.assignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apexascent.assignment.data.TarotCard
@Entity
data class TarotResult(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val result: List<TarotCard>,
    val userQuestion: String,
    val time: String
)
