package com.effectivelab1.heroapp.ui.screens.mainScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun HeroBackgroundTriangle(selectedColor: Color) {
    val animatedColor = animateColorAsState(targetValue = selectedColor, animationSpec = tween(600))

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
        ) {
            drawPath(
                path = Path().apply {
                    moveTo(size.width, size.height)
                    lineTo(0f, size.height)
                    lineTo(size.width, 0f)
                    close()
                },
                color = animatedColor.value,
            )
        }
    }
}
