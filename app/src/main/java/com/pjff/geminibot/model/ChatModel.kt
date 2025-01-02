package com.pjff.geminibot.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//Vid 308-room
@Entity(tableName = "chatbot")
data class ChatModel(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val chat:String,
    val role: String
)