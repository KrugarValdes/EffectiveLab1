package com.effectivelab1.heroapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.effectivelab1.heroapp.R
import com.effectivelab1.heroapp.ui.components.HeroCard
import com.effectivelab1.heroapp.model.Hero
import com.effectivelab1.heroapp.ui.components.DataProvider
import androidx.compose.foundation.lazy.items
@Composable
fun HeroListScreen(navController: NavController) {
    val context = LocalContext.current
    val heroesList: List<Hero> = DataProvider.loadHeroes(context)

    Column {
        Image(
            painter = painterResource(R.drawable.marvel_logo),
            contentDescription = stringResource(R.string.marvel_logo_description)
        )
        Text(
            text = stringResource(R.string.choose_your_hero),
            style = MaterialTheme.typography.headlineSmall
        )
        LazyColumn {
            items(heroesList) { hero ->
                HeroCard(hero = hero, onClick = {
                    navController.navigate("heroDetails/${hero.id}")
                })
            }
        }
    }
}