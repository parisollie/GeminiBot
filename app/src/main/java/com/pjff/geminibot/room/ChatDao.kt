package com.pjff.geminibot.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pjff.geminibot.model.ChatModel

//Vid 308
@Dao
interface ChatDao {
    @Query("SELECT * FROM chatbot ORDER BY  id ASC")
    suspend fun getChat(): List<ChatModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(item: ChatModel)

    @Query("DELETE FROM chatbot")
    suspend fun deleteChat()

}