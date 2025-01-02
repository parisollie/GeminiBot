package com.pjff.geminibot.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pjff.geminibot.model.ChatModel

//Vid 308
@Database(entities = [ChatModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun chatDao(): ChatDao
}