package com.example.coco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.coco.presentation.LoadingScreen
import com.example.coco.presentation.LoginScreen
import com.example.coco.presentation.getStarted.GetStartedScreen
import com.example.coco.presentation.getStarted.GetStartedViewModel
import com.example.coco.ui.theme.CocoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val getStartedViewModel = GetStartedViewModel()
                    GetStartedScreen( getStartedViewModel )

                }
            }
        }
    }
}