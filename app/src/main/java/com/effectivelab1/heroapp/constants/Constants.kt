package com.effectivelab1.heroapp.constants

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.effectivelab1.heroapp.R

object Constants {
    // ListScreen
    val heroListCardHeight = 530.dp
    val horizontalPadding = 40.dp
    val spacerBetweenItems = 20.dp
    val marvelLogoWidth = 192.dp
    val marvelLogoHeight = 40.dp
    val screenTitleFontSize = 28.sp
    val screenTitleTopPadding = 40.dp
    val screenTitleBottomPadding = 80.dp
    val screenTriangleSize = 0.6f
    val screenTriangleDuration = 600

    // DetailScreem
    val heroInfoBottomPadding = 45.dp
    val heroInfoStartPadding = 10.dp
    val heroNameFontSize = 34.sp
    val heroDescriptionFontSize = 22.sp
    val heroDescriptionLineHeight = 30.sp
    val iconButtonPaddingStart = PaddingValues(start = 25.dp, top = 35.dp)
    val sizeIconArrowBack = DpSize(30.dp, 30.dp)

    // HeroCard
    val heroCardWidth = 320.dp
    val heroCardHeight = 530.dp
    val heroCardShadowElevation = 8.dp
    val heroCardCornerShape = 16.dp
    val heroCardNameFontSize = 30.sp
    val heroCardTextStartPadding = 25.dp
    val heroCardTextBottomPadding = 30.dp

    // Font
    val interFontFamily =
        FontFamily(
            Font(R.font.inter_extrabold, FontWeight.ExtraBold),
            Font(R.font.inter_bold, FontWeight.Bold),
        )
}
