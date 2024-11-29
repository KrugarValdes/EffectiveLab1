package com.effectivelab1.heroapp.presentation.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.effectivelab1.heroapp.util.Constants.iconButtonPaddingStart
import com.effectivelab1.heroapp.util.Constants.sizeIconArrowBack

@Composable
fun NavigationBackButton(
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
