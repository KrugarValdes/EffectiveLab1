package com.effectivelab1.heroapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter
import com.effectivelab1.heroapp.presentation.screens.heroInfoScreen.HeroDetailScreen
import com.effectivelab1.heroapp.presentation.viewModel.CharacterViewModel
import com.effectivelab1.heroapp.ui.screens.mainScreen.HeroListScreen

import com.google.gson.Gson

@Composable
fun NavGraph(
    navController: NavHostController,
    onItemChanged: (Int) -> Unit,
) {
    val viewModel: CharacterViewModel = remember { CharacterViewModel() }

    NavHost(navController = navController, startDestination = "heroList") {
        composable("heroList") {
            HeroListScreen(navController = navController, viewModel = viewModel, onItemChanged = onItemChanged)
        }
        composable("hero_details/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")?.toIntOrNull()

            // Загружаем героя перед отображением экрана
            if (heroId != null) {
                viewModel.loadHeroById(heroId)
            }

            // Подписываемся на изменения состояния выбранного героя
            val hero by viewModel.selectedHero.collectAsState(initial = null)

            HeroDetailScreen(currentHero = hero, navigator = navController)
        }
    }
}