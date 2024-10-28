package com.effectivelab1.heroapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.effectivelab1.heroapp.navigation.NavGraph
import com.effectivelab1.heroapp.ui.theme.HeroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HeroAppTheme {
                val navController = rememberNavController()
                NavGraph(
                    navController = navController,
                    onItemChanged = { index -> }
                )
            }
        }
    }
}


