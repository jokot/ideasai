package com.example.ideasai.di

import com.example.ideasai.core.domain.repository.FavoriteRepository
import com.example.ideasai.core.domain.repository.FavoriteRepositoryImpl
import com.example.ideasai.core.domain.repository.IdeaRepository
import com.example.ideasai.core.domain.repository.IdeaRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindIdeaRepository(
        ideaRepositoryImpl: IdeaRepositoryImpl
    ): IdeaRepository

    @Binds
    abstract fun bindFavoriteRepository(
        favoriteRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository
}