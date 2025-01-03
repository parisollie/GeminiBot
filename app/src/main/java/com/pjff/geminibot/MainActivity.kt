package com.pjff.geminibot
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pjff.geminibot.ui.theme.GeminiBotTheme
import com.pjff.geminibot.viewModel.GeminiViewModel
import com.pjff.geminibot.views.HomeView
import com.pjff.geminibot.views.ModalView


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Vid 306
        val viewModel: GeminiViewModel by viewModels()
        setContent {
            GeminiBotTheme {
                HomeView(viewModel)
                //ModalView()
            }
        }
    }
}





/*import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.pjff.geminibot.ui.theme.GeminiBotTheme
import com.pjff.geminibot.viewModel.GeminiViewModel
import com.pjff.geminibot.views.HomeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GeminiBotTheme {
                HomeView()
                //ModalView()
            }
        }
    }
}*/




