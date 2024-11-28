package com.effectivelab1.heroapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.effectivelab1.heroapp.presentation.screens.HeroDetailScreen
import com.effectivelab1.heroapp.presentation.screens.HeroListScreen
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
                onItemChanged = onItemChanged,
            )
        }
        composable("hero_details/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")?.toIntOrNull()

            HeroDetailScreen(
                heroId = heroId,
                viewModel = viewModel,
                navigator = navController,
            )
        }
    }
}
