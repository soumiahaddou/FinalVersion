package com.apexascent.assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [TarotResult::class], version = 1
)
@TypeConverters(Converters::class)

abstract class TarotDatabase : RoomDatabase() {
    abstract val dao : TarotDao
}