package com.effectivelab1.heroapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.effectivelab1.heroapp.R
import com.effectivelab1.heroapp.constants.Constants
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter

@Composable
fun HeroCard(
    hero: MarvelCharacter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    fontSize: Float,
    textPadding: Float,
) {
    val cardShape = RoundedCornerShape(Constants.heroCardCornerShape)
    val backgroundColor = colorResource(id = R.color.card_background)

    Card(
        modifier =
        modifier
            .shadow(
                elevation = Constants.heroCardShadowElevation,
                shape = cardShape,
                clip = true,
            ).clip(cardShape)
            .clickable { onClick() },
    ) {
        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentAlignment = Alignment.BottomStart,
        ) {
            ImageLoader(
                imageUrl = hero.imageUrl,
                contentDescription = hero.name,
                modifier = Modifier.fillMaxSize(),
            )

            Text(
                text = hero.name,
                fontSize = fontSize.sp,
                fontFamily = Constants.interFontFamily,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                style =
                TextStyle(
                    shadow =
                    Shadow(
                        color = Color.Black,
                        offset = Offset(2f, 2f),
                        blurRadius = 4f,
                    ),
                ),
                modifier =
                Modifier
                    .padding(
                        start = textPadding.dp,
                        bottom = textPadding.dp,
                    ),
            )
        }
    }
}
