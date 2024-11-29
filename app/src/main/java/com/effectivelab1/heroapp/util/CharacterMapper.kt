package com.effectivelab1.heroapp.util

import com.effectivelab1.heroapp.data.model.MarvelCharacterEntity
import com.effectivelab1.heroapp.data.model.MarvelCharacter
import com.effectivelab1.heroapp.data.model.MarvelCharacterUI
import com.effectivelab1.heroapp.data.model.MarvelImage

fun MarvelCharacter.toEntity(): MarvelCharacterEntity =
    MarvelCharacterEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl
    )

fun MarvelCharacterEntity.toUI(): MarvelCharacterUI =
    MarvelCharacterUI(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl
    )

fun MarvelCharacter.toUI(): MarvelCharacterUI =
    MarvelCharacterUI(
        id = this.id,
        name = this.name,
        description = this.description,
        imageUrl = this.imageUrl
    )