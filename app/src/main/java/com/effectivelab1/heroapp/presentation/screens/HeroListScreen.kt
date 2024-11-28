package com.effectivelab1.heroapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.effectivelab1.heroapp.R
import com.effectivelab1.heroapp.util.Constants
import com.effectivelab1.heroapp.data.model.MarvelCharacter
import com.effectivelab1.heroapp.data.model.MarvelCharacterUI
import com.effectivelab1.heroapp.presentation.screens.components.BackgroundTriangle
import com.effectivelab1.heroapp.presentation.screens.components.ErrorMessage
import com.effectivelab1.heroapp.presentation.screens.components.LoadingText
import com.effectivelab1.heroapp.presentation.screens.components.ReloadButton
import com.effectivelab1.heroapp.presentation.viewModel.CharacterViewModel
import com.effectivelab1.heroapp.ui.screens.mainScreen.HeroListCard

@Composable
fun HeroListScreen(
    navController: NavController,
    viewModel: CharacterViewModel,
    onItemChanged: (Int) -> Unit,
) {
    val heroesList by viewModel.heroes.collectAsState()
    val selectedColor = viewModel.triangleColor
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Box(
        modifier =
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        BackgroundTriangle(selectedColor)

        Column(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(bottom = Constants.screenTitleBottomPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Constants.reloadButtonPaddingHorisontal),
                contentAlignment = Alignment.Center,
            ) {
                MarvelLogo()
                ReloadButton(
                    viewModel = viewModel,
                    modifier =
                    Modifier
                        .align(Alignment.CenterEnd)
                        .padding(top = Constants.reloadButtonPaddingTop),
                )
            }

            ScreenTitle()
            ErrorMessage(errorMessage)
            if (isLoading and heroesList.isEmpty() and (errorMessage == null)) {
                LoadingText()
            } else {
                HeroListContent(
                    heroesList = heroesList,
                    navController = navController,
                    viewModel = viewModel,
                    onItemChanged = onItemChanged,
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
        modifier =
        Modifier
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
        modifier =
        Modifier.padding(
            top = Constants.screenTitleTopPadding,
            bottom = Constants.screenTitleBottomPadding,
        ),
    )
}

@Composable
fun HeroListContent(
    heroesList: List<MarvelCharacterUI>,
    navController: NavController,
    viewModel: CharacterViewModel,
    onItemChanged: (Int) -> Unit,
) {
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
        onScrolledToEnd = { viewModel.onListScrolledToEnd() },
    )
}
