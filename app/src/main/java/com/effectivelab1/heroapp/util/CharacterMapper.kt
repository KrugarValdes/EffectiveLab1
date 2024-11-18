package com.effectivelab1.heroapp.util

import com.effectivelab1.heroapp.data.MarvelCharacterEntity
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter
import com.effectivelab1.heroapp.presentation.models.MarvelImage


fun MarvelCharacter.toEntity(): MarvelCharacterEntity =
    MarvelCharacterEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl
    )

fun MarvelCharacterEntity.toUI(): MarvelCharacter =
    MarvelCharacter(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnail = MarvelImage(
            path = this.imageUrl.substringBeforeLast('.'),
            extension = this.imageUrl.substringAfterLast('.')
        )
    )
