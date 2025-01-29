package com.example.ideasai.di

import com.example.ideasai.core.domain.repository.FavoriteRepository
import com.example.ideasai.core.domain.repository.IdeaRepository
import com.example.ideasai.core.domain.usecase.GenerateIdeasUseCase
import com.example.ideasai.core.domain.usecase.GetFavoritesUseCase
import com.example.ideasai.core.domain.usecase.ToggleFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGenerateIdeasUseCase(
        ideaRepository: IdeaRepository
    ): GenerateIdeasUseCase = GenerateIdeasUseCase(ideaRepository)

    @Provides
    @Singleton
    fun provideGetFavoritesUseCase(
        favoriteRepository: FavoriteRepository
    ): GetFavoritesUseCase = GetFavoritesUseCase(favoriteRepository)

    @Provides
    @Singleton
    fun provideToggleFavoriteUseCase(
        favoriteRepository: FavoriteRepository
    ): ToggleFavoriteUseCase = ToggleFavoriteUseCase(favoriteRepository)
}