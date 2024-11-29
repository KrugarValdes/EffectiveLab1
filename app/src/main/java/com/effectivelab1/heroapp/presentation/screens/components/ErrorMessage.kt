package com.effectivelab1.heroapp.presentation.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.effectivelab1.heroapp.util.Constants

@Composable
fun ErrorMessage(errorMessage: String?) {
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = Color.White,
            fontSize = Constants.errorMessageFontSize,
            fontFamily = Constants.interFontFamily,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(Constants.errorMessagePadding),
        )
    }
}
