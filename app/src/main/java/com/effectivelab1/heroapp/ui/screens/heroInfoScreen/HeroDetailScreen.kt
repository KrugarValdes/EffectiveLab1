package com.effectivelab1.heroapp.ui.screens.heroInfoScreen

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.effectivelab1.heroapp.constants.Constants
import com.effectivelab1.heroapp.constants.Constants.iconButtonPaddingStart
import com.effectivelab1.heroapp.constants.Constants.sizeIconArrowBack
import com.effectivelab1.heroapp.model.Hero
import com.effectivelab1.heroapp.ui.components.HeroImage

@Composable
fun HeroDetailScreen(
    currentHero: Hero?,
    navigator: NavController,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        currentHero?.let { DisplayHeroImage(it.imageUrl, it.name) }
        HeroInformation(currentHero)
        NavigationBackButton(
            navigator = navigator,
        )
    }
}

@Composable
private fun DisplayHeroImage(
    imageUrl: String,
    contentDescription: String,
) {
    HeroImage(
        imageUrl = imageUrl,
        contentDescription = contentDescription,
        modifier = Modifier.fillMaxSize(),
    )
}

@Composable
private fun HeroInformation(currentHero: Hero?) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(bottom = Constants.heroInfoBottomPadding, start = Constants.heroInfoStartPadding),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
    ) {
        HeroName(name = currentHero?.name ?: "Unknown Hero")
        HeroDescription(description = currentHero?.description ?: "No description available.")
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
        modifier = Modifier.padding(bottom = 8.dp),
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
            modifier = Modifier.size(sizeIconArrowBack),
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
        )
    }
}
