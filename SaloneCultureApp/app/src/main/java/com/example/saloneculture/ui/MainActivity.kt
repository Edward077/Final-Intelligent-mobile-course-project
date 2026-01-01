package com.example.saloneculture.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.saloneculture.ui.salone.SaloneCultureApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent { com.example.saloneculture.ui.theme.JetnewsTheme { SaloneCultureApp() } }
    }
}
