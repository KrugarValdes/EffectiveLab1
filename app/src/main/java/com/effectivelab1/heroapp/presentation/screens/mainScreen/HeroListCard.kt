package com.effectivelab1.heroapp.ui.screens.mainScreen

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.effectivelab1.heroapp.constants.Constants
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter
import com.effectivelab1.heroapp.ui.components.HeroCard
import kotlin.math.abs

@Composable
fun HeroListCard(
    heroesList: List<MarvelCharacter>,
    onHeroClick: (MarvelCharacter) -> Unit,
    onItemChanged: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState)

    val currentItem by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.minByOrNull { abs(it.offset) }?.index ?: 0
        }
    }

    LaunchedEffect(currentItem) {
        onItemChanged(currentItem)
    }

    LazyRow(
        state = lazyListState,
        flingBehavior = snapFlingBehavior,
        modifier = Modifier
            .fillMaxWidth()
            .height(Constants.heroListCardHeight),
        contentPadding = PaddingValues(horizontal = Constants.horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(Constants.spacerBetweenItems)
    ) {
        itemsIndexed(heroesList) { index, hero ->
            val scale by animateFloatAsState(
                targetValue = if (index == currentItem) 1f else 0.9f,
                animationSpec = tween(durationMillis = 700, easing = LinearOutSlowInEasing)
            )

            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                HeroCard(
                    hero = hero,
                    scale = scale,
                    onClick = { onHeroClick(hero) }
                )
            }
        }
    }
}