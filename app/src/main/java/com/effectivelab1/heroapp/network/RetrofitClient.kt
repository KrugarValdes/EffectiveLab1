package com.effectivelab1.heroapp.network

import com.effectivelab1.heroapp.constants.ApiKeys.BASE_URL
import com.effectivelab1.heroapp.constants.ApiKeys.PRIVATE_KEY
import com.effectivelab1.heroapp.constants.ApiKeys.PUBLIC_KEY
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {
    private val moshi =
        Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    private val logging =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val marvelInterceptor = MarvelApiInterceptor(PUBLIC_KEY, PRIVATE_KEY)

    private val client =
        OkHttpClient
            .Builder()
            .addInterceptor(logging)
            .addInterceptor(marvelInterceptor)
            .build()

    val apiService: MarvelApiService by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(MarvelApiService::class.java)
    }
}
