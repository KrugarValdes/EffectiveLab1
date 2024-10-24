package com.effectivelab1.heroapp.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.effectivelab1.heroapp.ui.screens.HeroDetailScreen
import com.effectivelab1.heroapp.ui.screens.HeroListScreen
import com.effectivelab1.heroapp.ui.screens.HeroListScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "heroList") {
        composable("heroList") {
            HeroListScreen()
        }
        composable("heroDetails/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")
            HeroDetailScreen(heroId) // Передаем heroId в экран деталей
        }
    }
}