package com.effectivelab1.heroapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.effectivelab1.heroapp.util.Constants
import com.effectivelab1.heroapp.presentation.screens.components.ImageLoader
import com.effectivelab1.heroapp.data.model.MarvelCharacter
import com.effectivelab1.heroapp.data.model.MarvelCharacterUI
import com.effectivelab1.heroapp.presentation.screens.components.LoadingText
import com.effectivelab1.heroapp.presentation.viewModel.CharacterViewModel
import com.effectivelab1.heroapp.presentation.screens.components.NavigationBackButton

@Composable
fun HeroDetailScreen(
    heroId: Int?,
    viewModel: CharacterViewModel,
    navigator: NavController,
) {
    if (heroId != null) {
        viewModel.loadHeroById(heroId)
    }
    val currentHero = viewModel.selectedHero.collectAsState(initial = null).value

    if (currentHero == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            NavigationBackButton(navigator = navigator)
            LoadingText()
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            ImageLoader(
                imageUrl = currentHero.imageUrl,
                contentDescription = currentHero.name,
                modifier = Modifier.fillMaxSize(),
            )
            HeroInformation(currentHero)
            NavigationBackButton(navigator = navigator)
        }
    }
}

@Composable
private fun HeroInformation(currentHero: MarvelCharacterUI) {
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(bottom = Constants.heroInfoBottomPadding, start = Constants.heroInfoStartPadding),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
    ) {
        HeroName(name = currentHero.name)
        HeroDescription(description = currentHero.description.ifEmpty { "No description available." })
    }
}

@Composable
private fun HeroName(name: String) {
    Text(
        text = name,
        fontSize = Constants.heroNameFontSize,
        fontFamily = Constants.interFontFamily,
        fontWeight = FontWeight.ExtraBold,
        color = Color.White,
        style =
        TextStyle(
            shadow =
            Shadow(
                color = Color.Black,
                offset = Offset(2f, 2f),
                blurRadius = 4f,
            ),
        ),
        modifier = Modifier.padding(bottom = Constants.heroNameBottomPadding),
    )
}

@Composable
private fun HeroDescription(description: String) {
    Text(
        text = description,
        fontSize = Constants.heroDescriptionFontSize,
        lineHeight = Constants.heroDescriptionLineHeight,
        fontFamily = Constants.interFontFamily,
        fontWeight = FontWeight.ExtraBold,
        color = Color.White,
        style =
        TextStyle(
            shadow =
            Shadow(
                color = Color.Black,
                offset = Offset(2f, 2f),
                blurRadius = 4f,
            ),
        ),
    )
}


