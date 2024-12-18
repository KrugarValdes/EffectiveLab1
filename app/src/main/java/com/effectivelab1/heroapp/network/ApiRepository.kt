package com.effectivelab1.heroapp.network

import com.effectivelab1.heroapp.constants.ApiKeys
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter

class ApiRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getCharacters(offset: Int): List<MarvelCharacter> =
        apiService.getCharacters(offset = offset, limit = ApiKeys.LIMIT).data.results

    suspend fun getCharacter(characterId: Int): MarvelCharacter =
        apiService
            .getCharacter(characterId)
            .data.results
            .first()
}
