package com.effectivelab1.heroapp.presentation.screens.heroInfoScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.effectivelab1.heroapp.constants.Constants
import com.effectivelab1.heroapp.constants.Constants.iconButtonPaddingStart
import com.effectivelab1.heroapp.constants.Constants.sizeIconArrowBack
import com.effectivelab1.heroapp.presentation.components.ImageLoader
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter

@Composable
fun HeroDetailScreen(
    currentHero: MarvelCharacter?,
    navigator: NavController,
) {
    if (currentHero == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            NavigationBackButton(navigator = navigator)
            Text(
                text = "Loading...",
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center),
            )
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
private fun HeroInformation(currentHero: MarvelCharacter) {
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
                blurRadius = 4f
            )
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
                blurRadius = 4f
            )
        )
    )
}

@Composable
private fun NavigationBackButton(
    navigator: NavController,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = { navigator.popBackStack() },
        modifier =
        modifier
            .padding(iconButtonPaddingStart)
            .size(sizeIconArrowBack),
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.LightGray,
        )
    }
}
