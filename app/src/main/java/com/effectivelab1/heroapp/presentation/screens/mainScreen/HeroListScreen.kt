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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.effectivelab1.heroapp.R
import com.effectivelab1.heroapp.constants.Constants
import com.effectivelab1.heroapp.presentation.screens.mainScreen.HeroBackgroundTriangle
import com.effectivelab1.heroapp.presentation.viewModel.CharacterViewModel

@Composable
fun HeroListScreen(
    navController: NavController,
    viewModel: CharacterViewModel,
    onItemChanged: (Int) -> Unit,
) {
    val heroesList = viewModel.heroes.collectAsState().value
    val selectedColor = viewModel.triangleColor

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        HeroBackgroundTriangle(selectedColor)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = Constants.screenTitleBottomPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MarvelLogo()
            ScreenTitle()
            if (viewModel.errorMessage.value != null) {
                Text(
                    text = viewModel.errorMessage.value!!,
                    color = Color.White,
                    fontSize = Constants.errorMessageFontSize,
                    fontFamily = Constants.interFontFamily,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(Constants.errorMessagePadding))
            } else {
                HeroListCard(
                    heroesList = heroesList,
                    onHeroClick = { hero ->
                        viewModel.resetSelectedHero()
                        navController.navigate("hero_details/${hero.id}")
                    },
                    onItemChanged = { index ->
                        viewModel.selectHero(index)
                        onItemChanged(index)
                    },
                )
            }
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
        modifier = Modifier.padding(
            top = Constants.screenTitleTopPadding,
            bottom = Constants.screenTitleBottomPadding,
        ),
    )
}
