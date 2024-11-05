package com.effectivelab1.heroapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.effectivelab1.heroapp.R
import com.effectivelab1.heroapp.constants.Constants
import com.effectivelab1.heroapp.presentation.components.HeroImage
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter

@Composable
fun HeroCard(
    hero: MarvelCharacter,
    onClick: () -> Unit,
    scale: Float,
    modifier: Modifier = Modifier,
) {
    val cardShape = RoundedCornerShape(Constants.heroCardCornerShape)
    val backgroundColor = colorResource(id = R.color.card_background)

    Card(
        modifier = modifier
            .size(
                width = (Constants.heroCardWidth.value * scale).dp,
                height = (Constants.heroCardHeight.value * scale).dp,
            )
            .shadow(
                elevation = Constants.heroCardShadowElevation,
                shape = cardShape,
                clip = true,
            )
            .clip(cardShape)
            .clickable { onClick() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor),
            contentAlignment = Alignment.BottomStart,
        ) {
            HeroImage(
                imageUrl = hero.imageUrl,
                contentDescription = hero.name,
                modifier = Modifier.fillMaxSize(),
            )

            Text(
                text = hero.name,
                fontSize = (Constants.heroCardNameFontSize.value * scale).sp,
                fontFamily = Constants.interFontFamily,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        start = (Constants.heroCardTextStartPadding.value * scale).dp,
                        bottom = (Constants.heroCardTextBottomPadding.value * scale).dp,
                    ),
            )
        }
    }
}