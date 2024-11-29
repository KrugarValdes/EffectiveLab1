package com.effectivelab1.heroapp.di

import com.effectivelab1.heroapp.data.database.MarvelCharacterDao
import com.effectivelab1.heroapp.data.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideMarvelRepository(dao: MarvelCharacterDao): MarvelRepository = MarvelRepository(dao)
}
