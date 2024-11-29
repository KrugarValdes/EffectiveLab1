package com.effectivelab1.heroapp.di


import com.effectivelab1.heroapp.data.api.ApiKeys
import com.effectivelab1.heroapp.network.ApiRepository
import com.effectivelab1.heroapp.network.MarvelApiInterceptor
import com.effectivelab1.heroapp.network.MarvelApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideMarvelInterceptor(): MarvelApiInterceptor {
        return MarvelApiInterceptor(ApiKeys.PUBLIC_KEY, ApiKeys.PRIVATE_KEY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        marvelInterceptor: MarvelApiInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(marvelInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiKeys.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MarvelApiService {
        return retrofit.create(MarvelApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiRepository(apiService: MarvelApiService): ApiRepository {
        return ApiRepository(apiService)
    }
}