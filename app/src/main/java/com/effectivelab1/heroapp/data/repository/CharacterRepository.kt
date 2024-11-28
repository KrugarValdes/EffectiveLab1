package com.effectivelab1.heroapp.data.repository

import com.effectivelab1.heroapp.data.database.MarvelCharacterDao
import com.effectivelab1.heroapp.network.ApiRepository
import com.effectivelab1.heroapp.data.model.MarvelCharacter
import com.effectivelab1.heroapp.data.model.MarvelCharacterUI
import com.effectivelab1.heroapp.util.toEntity
import com.effectivelab1.heroapp.util.toUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MarvelRepository(
    private val dao: MarvelCharacterDao
) {
    fun getCharacters(): Flow<List<MarvelCharacterUI>> =
        dao.getAllCharacters().map { entities ->
            entities.map { it.toUI() }
        }

    suspend fun getCharacterCount(): Int {
        return dao.getCharacterCount()
    }

    suspend fun clearDatabase() {
        dao.clearCharacters()
    }

    suspend fun refreshCharacters(offset: Int, apiRepository: ApiRepository) {
        val characters = apiRepository.getCharacters(offset)
        dao.insertCharacters(characters.map { it.toEntity() })
    }

    suspend fun getCharacterById(id: Int, apiRepository: ApiRepository): MarvelCharacterUI? {
        val localCharacter = dao.getCharacterById(id)?.toUI()
        if (localCharacter != null) return localCharacter

        val remoteCharacter = apiRepository.getCharacter(id)
        remoteCharacter?.let {
            dao.insertCharacter(it.toEntity())
            return it.toUI()
        }
        return null
    }
}
