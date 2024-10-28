package com.effectivelab1.heroapp.model

import androidx.compose.ui.graphics.Color

data class Hero(
    var id: String,
    var name: String,
    var imageUrl: String,
    var description: String,
    var colorResourceId: Int, // ID ресурса цвета
    var color: Color, // Поле для хранения цвета
)
