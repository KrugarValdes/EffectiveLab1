package com.effectivelab1.heroapp.ui.screens.mainScreen

import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.effectivelab1.heroapp.data.model.MarvelCharacterUI
import com.effectivelab1.heroapp.presentation.screens.components.HeroCard
import com.effectivelab1.heroapp.util.Constants
import kotlin.math.abs

@Composable
fun HeroListCard(
    heroesList: List<MarvelCharacterUI>,
    onHeroClick: (MarvelCharacterUI) -> Unit,
    onItemChanged: (Int) -> Unit,
    onScrolledToEnd: () -> Unit,
) {
    val lazyListState = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(lazyListState)

    val currentItem by rememberUpdatedState(newValue = getCurrentVisibleItem(lazyListState))

    LaunchedEffect(currentItem) {
        onItemChanged(currentItem)
    }
    val lastVisibleItemIndex = remember { mutableStateOf(-1) }

    LaunchedEffect(lazyListState.layoutInfo) {
        val newLastVisibleItemIndex =
            lazyListState.layoutInfo.visibleItemsInfo
                .lastOrNull()
                ?.index ?: -1
        val totalItemCount = heroesList.size

        if (newLastVisibleItemIndex != lastVisibleItemIndex.value) {
            Log.d("HeroList", "Size: $totalItemCount, LastVisibleItemIndex: $newLastVisibleItemIndex")

            if (newLastVisibleItemIndex == totalItemCount - 1) {
                Log.d("HeroList", "Updating DB")
                onScrolledToEnd()
            }
            lastVisibleItemIndex.value = newLastVisibleItemIndex
        }
    }

    LazyRow(
        state = lazyListState,
        flingBehavior = snapFlingBehavior,
        modifier =
            Modifier
                .fillMaxWidth()
                .height(Constants.heroListCardHeight),
        contentPadding = PaddingValues(horizontal = Constants.horizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(Constants.spacerBetweenItems),
    ) {
        itemsIndexed(heroesList) { index, hero ->
            HeroItem(
                hero = hero,
                isSelected = index == currentItem,
                onHeroClick = { onHeroClick(hero) },
            )
        }
    }
}

@Composable
fun HeroItem(
    hero: MarvelCharacterUI,
    isSelected: Boolean,
    onHeroClick: () -> Unit,
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.9f,
        animationSpec = tween(durationMillis = Constants.cardAnimationDuration, easing = LinearOutSlowInEasing),
    )

    val cardWidth = Constants.heroCardWidth.value * scale
    val cardHeight = Constants.heroCardHeight.value * scale
    val fontSize = Constants.heroCardNameFontSize.value * scale
    val textPadding = Constants.heroCardTextPadding.value * scale

    Box(
        modifier = Modifier.fillMaxHeight(),
        contentAlignment = Alignment.Center,
    ) {
        HeroCard(
            hero = hero,
            onClick = onHeroClick,
            modifier = Modifier.size(width = cardWidth.dp, height = cardHeight.dp),
            fontSize = fontSize,
            textPadding = textPadding,
        )
    }
}

fun getCurrentVisibleItem(lazyListState: LazyListState): Int =
    lazyListState.layoutInfo.visibleItemsInfo
        .minByOrNull { abs(it.offset) }
        ?.index ?: 0
