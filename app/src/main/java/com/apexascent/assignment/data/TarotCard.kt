package com.apexascent.assignment.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TarotCard(
    val imageId: Int,
    val imageMeaning: List<String>
): Parcelable
