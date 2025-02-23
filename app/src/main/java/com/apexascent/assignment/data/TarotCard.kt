package com.apexascent.assignment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TarotCard(
    val cardId: Int,
    val cardName: String,
    val cardMeaning: List<String>
): Parcelable
