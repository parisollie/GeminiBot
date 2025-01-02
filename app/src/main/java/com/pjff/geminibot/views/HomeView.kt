package com.pjff.geminibot.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.pjff.geminibot.components.GlobeMessage
import com.pjff.geminibot.components.MessageInput
import com.pjff.geminibot.components.Title
import com.pjff.geminibot.ui.theme.backColor
import com.pjff.geminibot.viewModel.GeminiViewModel

//Vid 304
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//Vid 306
fun HomeView(viewModel: GeminiViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Title() },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backColor
                ),
                //Vid 310
                actions = {
                    IconButton(onClick = { viewModel.deleteChat() }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }


                }
            )
        }
    ) { pad ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(pad)
                .background(backColor)
        ) {
            //Vid 306
            ChatContent(modifier = Modifier.weight(1f), viewModel)
            //Vid 304
            MessageInput {
                viewModel.sendMessage(it)
            }

            /*var showModal by remember {
                mutableStateOf(false)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { showModal = true}) {
                    Icon(imageVector = Icons.Filled.Camera, contentDescription = "Camera")
                }
                MessageInput {
                    viewModel.sendMessage(it)
                }
            }
            ModalView(viewModel, showModal) {
                viewModel.cleanVars()
                showModal = false
            }*/


        }
    }
}

//Vid 306
@Composable
fun ChatContent(modifier: Modifier, viewModel: GeminiViewModel) {
    //Vid 309
    LaunchedEffect(Unit) {
        viewModel.loadChat()
    }
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        items(viewModel.messageList.reversed()) {
            GlobeMessage(messageModel = it)
        }
    }
}







