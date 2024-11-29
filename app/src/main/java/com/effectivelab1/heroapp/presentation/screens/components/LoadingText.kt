package com.effectivelab1.heroapp.presentation.screens.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.effectivelab1.heroapp.R
import com.effectivelab1.heroapp.util.Constants

@Composable
fun LoadingText() {
    Text(
        text = stringResource(R.string.loading_string),
        color = Color.White,
        fontSize = Constants.heroNameFontSize,
        fontFamily = Constants.interFontFamily,
        fontWeight = FontWeight.ExtraBold,
    )
}
