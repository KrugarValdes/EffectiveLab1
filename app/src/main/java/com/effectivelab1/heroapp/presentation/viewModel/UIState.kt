package com.effectivelab1.heroapp.presentation.viewModel

import com.effectivelab1.heroapp.data.model.MarvelCharacterUI
import androidx.compose.ui.graphics.Color

data class UIState(
    val heroes: List<MarvelCharacterUI> = emptyList(),
    val selectedHero: MarvelCharacterUI? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val triangleColor: Color = Color.Cyan
)

