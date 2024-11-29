package com.effectivelab1.heroapp.di

import android.content.Context
import androidx.room.Room
import com.effectivelab1.heroapp.data.database.AppDatabase
import com.effectivelab1.heroapp.data.database.MarvelCharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "marvel-database"
        ).build()
    }

    @Provides
    fun provideMarvelCharacterDao(database: AppDatabase): MarvelCharacterDao {
        return database.marvelCharacterDao()
    }
}