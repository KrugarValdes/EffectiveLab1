package com.effectivelab1.heroapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.effectivelab1.heroapp.data.MarvelRepository
import com.effectivelab1.heroapp.presentation.screens.heroInfoScreen.HeroDetailScreen
import com.effectivelab1.heroapp.presentation.screens.mainScreen.HeroListScreen
import com.effectivelab1.heroapp.presentation.viewModel.CharacterViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: CharacterViewModel,
    onItemChanged: (Int) -> Unit,
) {
    NavHost(navController = navController, startDestination = "heroList") {
        composable("heroList") {
            HeroListScreen(
                navController = navController,
                viewModel = viewModel,
                onItemChanged = onItemChanged
            )
        }
        composable("hero_details/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")?.toIntOrNull()

            HeroDetailScreen(
                heroId = heroId,
                viewModel = viewModel,
                navigator = navController
            )
        }
    }
}