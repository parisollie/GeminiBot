package com.pjff.geminibot.viewModel

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.pjff.geminibot.BuildConfig
import com.pjff.geminibot.model.ChatModel
import com.pjff.geminibot.model.MessageModel
import com.pjff.geminibot.room.AppDatabase
import kotlinx.coroutines.launch



//class GeminiViewModel: ViewModel() {
//Vid 309
class GeminiViewModel(application: Application): AndroidViewModel(application) {

    //Vid 309, creamos la base de datos.
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "chat_bot"
    ).build()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apikey
    )

    //Vid 305,lazy retrasa el inicio de valores
    private val chat by lazy {
        generativeModel.startChat()
    }

    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }

    /*Vid 305
    fun sendMessage(question: String){
        viewModelScope.launch {
            try {
               messageList.add(MessageModel(question, role = "user"))
                val response = chat.sendMessage(question)
                messageList.add(MessageModel(response.text.toString(), role = "model"))
            }catch (e:Exception){
                messageList.add(MessageModel("Error en la conversacion: ${e.message}", role = "model"))
            }
        }
    }*/

    //Vid 309, ROOM
    fun sendMessage(question: String){
        viewModelScope.launch {
            try {
                messageList.add(MessageModel(question, role = "user"))
                //Vid 311
                val contextChat = messageList.joinToString (separator = "\n"){"${it.role}: ${it.message}"}
                val response = chat.sendMessage(contextChat)

                //val response = chat.sendMessage(question)
                messageList.add(MessageModel(response.text.toString(), role = "model"))
                // room
                val chatDao = db.chatDao()
                chatDao.insertChat(item = ChatModel(chat = question, role = "user"))
                chatDao.insertChat(item = ChatModel(chat = response.text.toString(), role = "model"))
            }catch (e:Exception){
                messageList.add(MessageModel("Error en la conversacion: ${e.message}", role = "model"))
            }
        }
    }
    //Vid 309
    fun loadChat(){
        try {
            viewModelScope.launch {
                val chatDao = db.chatDao()
                val savedChat = chatDao.getChat()
                messageList.clear()
                for (chat in savedChat){
                    messageList.add(MessageModel(message = chat.chat, role = chat.role))
                }
            }
        }catch (e:Exception){
            messageList.add(MessageModel("Error en cargar el chat: ${e.message}", role = "model"))
        }
    }

    //Vid 310
    fun deleteChat(){
        viewModelScope.launch {
            try {
                val chatDao = db.chatDao()
                chatDao.deleteChat()
                messageList.clear()
            }catch (e:Exception){
                messageList.add(MessageModel("Error en cargar el chat: ${e.message}", role = "model"))
            }
        }
    }


    //Vid 318,Enviar imagen a Gemini

    var descriptionResponse by mutableStateOf("")
        private set

    //Vid 319
    var image by mutableStateOf<Uri>(Uri.EMPTY)

    fun descriptionImage(bitmap: Bitmap){
        viewModelScope.launch {
            try {

                val inputContent = content {
                    image(bitmap)
                    text("Describe la imagen")
                }
                val response = generativeModel.generateContent(inputContent)
                descriptionResponse = response.text.toString()
            }catch (e: Exception){
                descriptionResponse = "Error al enviar la imagen"
            }
        }
    }
    //Vid 319
    fun cleanVars(){
        descriptionResponse = ""
        image = Uri.EMPTY
    }
}




