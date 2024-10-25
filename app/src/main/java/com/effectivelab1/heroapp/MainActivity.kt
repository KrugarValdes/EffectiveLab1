package com.effectivelab1.heroapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.effectivelab1.heroapp.ui.theme.Theme
import androidx.navigation.compose.rememberNavController
import com.effectivelab1.heroapp.ui.screens.HeroListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Theme {
                val navController = rememberNavController()
                HeroListScreen()
                }
            }
        }
    }


