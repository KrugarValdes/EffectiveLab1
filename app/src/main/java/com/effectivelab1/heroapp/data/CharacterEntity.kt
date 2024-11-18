package com.effectivelab1.heroapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "marvel_characters")
data class MarvelCharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)

