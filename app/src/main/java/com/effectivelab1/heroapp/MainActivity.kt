package com.effectivelab1.heroapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.effectivelab1.heroapp.data.database.AppDatabase
import com.effectivelab1.heroapp.data.repository.MarvelRepository
import com.effectivelab1.heroapp.navigation.NavGraph
import com.effectivelab1.heroapp.presentation.theme.HeroAppTheme
import com.effectivelab1.heroapp.presentation.viewModel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            HeroAppTheme {
                val navController = rememberNavController()

                NavGraph(
                    navController = navController,
                    viewModel = viewModel,
                    onItemChanged = { index ->
                        Log.d("MainActivity", "Selected item index: $index")
                    },
                )
            }
        }
    }
}
