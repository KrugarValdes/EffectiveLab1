package com.effectivelab1.heroapp.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.effectivelab1.heroapp.model.Hero
import com.effectivelab1.heroapp.ui.screens.heroInfoScreen.HeroDetailScreen
import com.effectivelab1.heroapp.ui.screens.mainScreen.HeroListScreen
import com.google.gson.Gson

@Composable
fun NavGraph(navController: NavHostController, onItemChanged: (Int) -> Unit) {
    val gson = Gson()
    NavHost(navController = navController, startDestination = "heroList") {
        composable("heroList") {
            HeroListScreen(navController = navController, onItemChanged = onItemChanged)
        }
        composable("hero_details?heroJson={heroJson}") { backStackEntry ->
            val heroJson = backStackEntry.arguments?.getString("heroJson")
            val hero = heroJson?.let { gson.fromJson(it, Hero::class.java) }

            HeroDetailScreen(currentHero = hero, navigator = navController)
        }
    }
}