package com.effectivelab1.heroapp.presentation.viewModel

import androidx.compose.ui.graphics.Color
import com.effectivelab1.heroapp.data.model.MarvelCharacterUI

data class UIState(
    val heroes: List<MarvelCharacterUI> = emptyList(),
    val selectedHero: MarvelCharacterUI? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val triangleColor: Color = Color.Cyan,
)
