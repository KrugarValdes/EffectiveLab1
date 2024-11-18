
package com.effectivelab1.heroapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MarvelCharacterDao {

    @Query("SELECT * FROM marvel_characters ORDER BY name ASC")
    fun getAllCharacters(): Flow<List<MarvelCharacterEntity>>

    @Query("DELETE FROM marvel_characters")
    suspend fun clearCharacters()

    @Query("SELECT * FROM marvel_characters WHERE id = :id LIMIT 1")
    suspend fun getCharacterById(id: Int): MarvelCharacterEntity?

    @Query("SELECT COUNT(*) FROM marvel_characters")
    suspend fun getCharacterCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<MarvelCharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: MarvelCharacterEntity)
}