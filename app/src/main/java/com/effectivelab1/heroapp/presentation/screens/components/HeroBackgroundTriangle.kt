package com.effectivelab1.heroapp.presentation.screens.components

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
import com.effectivelab1.heroapp.util.Constants

@Composable
fun BackgroundTriangle(selectedColor: Color) {
    val animatedColor =
        animateColorAsState(
            targetValue = selectedColor,
            animationSpec = tween(Constants.screenTriangleDuration),
            label = "SelectedColorAnimation",
        )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Canvas(
            modifier =
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(Constants.screenTriangleSize),
        ) {
            drawPath(
                path =
                Path().apply {
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
