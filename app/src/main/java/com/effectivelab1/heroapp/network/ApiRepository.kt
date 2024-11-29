package com.effectivelab1.heroapp.network

import com.effectivelab1.heroapp.data.api.ApiKeys
import com.effectivelab1.heroapp.data.model.MarvelCharacter
import javax.inject.Inject

class ApiRepository
    @Inject
    constructor(
        private val apiService: MarvelApiService,
    ) {
        suspend fun getCharacters(offset: Int): List<MarvelCharacter> =
            apiService.getCharacters(offset = offset, limit = ApiKeys.LIMIT).data.results

        suspend fun getCharacter(characterId: Int): MarvelCharacter =
            apiService
                .getCharacter(characterId)
                .data.results
                .first()
    }
