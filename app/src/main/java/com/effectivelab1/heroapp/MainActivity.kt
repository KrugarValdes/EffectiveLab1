package com.effectivelab1.heroapp


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.effectivelab1.heroapp.data.AppDatabase
import com.effectivelab1.heroapp.data.MarvelRepository
import com.effectivelab1.heroapp.navigation.NavGraph
import com.effectivelab1.heroapp.presentation.theme.HeroAppTheme
import com.effectivelab1.heroapp.presentation.viewModel.CharacterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(applicationContext)
        val dao = database.marvelCharacterDao()
        val repository = MarvelRepository(dao)

        setContent {
            HeroAppTheme {
                val viewModel = remember { CharacterViewModel(repository) }
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


