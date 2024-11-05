package com.effectivelab1.heroapp.network

import com.effectivelab1.heroapp.constants.ApiKeys
import com.effectivelab1.heroapp.presentation.models.MarvelCharacter
import com.effectivelab1.heroapp.presentation.models.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = ApiKeys.LIMIT,
        @Query("offset") offset: Int = 0,
    ): MarvelResponse<MarvelCharacter>

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharacter(
        @Path("characterId") characterId: Int,
    ): MarvelResponse<MarvelCharacter>
}
