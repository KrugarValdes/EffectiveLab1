package com.effectivelab1.heroapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.effectivelab1.heroapp.R

@Composable
fun HeroAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors =
        if (darkTheme) {
            darkColorScheme(
                background = colorResource(id = R.color.app_background),
                onSecondary = colorResource(id = R.color.white),
            )
        } else {
            lightColorScheme(
                background = colorResource(id = R.color.app_background_light),
                onSecondary = Color.Black,
            )
        }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content,
    )
}
