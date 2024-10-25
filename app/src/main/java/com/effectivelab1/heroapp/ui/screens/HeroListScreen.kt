package com.effectivelab1.heroapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeroListScreen() {
    val heroesList = DataProvider.loadHeroes(LocalContext.current)

    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.marvel_logo),
            contentDescription = stringResource(R.string.marvel_logo_description)
        )
        Text(
            text = stringResource(R.string.choose_your_hero),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(16.dp)
        )

        val lazyListState = rememberLazyListState()
        LazyRow(
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState),
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(heroesList) { hero ->
                HeroCard(hero = hero, onClick = {
            })
        }
    }
}}