package com.effectivelab1.heroapp.ui.screens.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.effectivelab1.heroapp.R
import com.effectivelab1.heroapp.model.Hero
import com.effectivelab1.heroapp.ui.components.DataProvider
import com.effectivelab1.heroapp.ui.theme.Constants
import com.google.gson.Gson

@Composable
fun HeroListScreen(navController: NavController, onItemChanged: (Int) -> Unit) {
    val heroesList = DataProvider.loadHeroes(LocalContext.current)
    val gson = Gson()

    var selectedColor by remember { mutableStateOf(Color.Red) }
    var selectedIndex by remember { mutableStateOf(0) }

    UpdateSelectedColorEffect(selectedIndex, heroesList) { color ->
        selectedColor = color
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HeroBackgroundTriangle(selectedColor)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = Constants.screenTitleBottomPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MarvelLogo()
            ScreenTitle()
            HeroListCard(
                heroesList = heroesList,
                onHeroClick = { hero ->
                    val heroJson = gson.toJson(hero)
                    navController.navigate("hero_details?heroJson=$heroJson")
                },
                onItemChanged = { index ->
                    selectedIndex = index
                    onItemChanged(index)
                }
            )
        }
    }
}


@Composable
fun MarvelLogo() {
    Image(
        painter = painterResource(R.drawable.marvel_logo),
        contentDescription = stringResource(R.string.marvel_logo_description),
        modifier = Modifier
            .padding(top = Constants.screenTitleTopPadding)
            .width(Constants.marvelLogoWidth)
            .height(Constants.marvelLogoHeight),
    )
}

@Composable
fun ScreenTitle() {
    Text(
        text = stringResource(R.string.choose_your_hero),
        fontSize = Constants.screenTitleFontSize,
        color = Color.White,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier.padding(top = Constants.screenTitleTopPadding,
            bottom = Constants.screenTitleBottomPadding),
    )
}

@Composable
fun UpdateSelectedColorEffect(
    selectedIndex: Int,
    heroesList: List<Hero>,
    onColorChange: (Color) -> Unit,
) {
    LaunchedEffect(selectedIndex) {
        if (selectedIndex in heroesList.indices) {
            onColorChange(heroesList[selectedIndex].color)
        }
    }
}
