package com.example.ideasai.core.domain.usecase

import com.example.ideasai.core.data.model.Idea
import com.example.ideasai.core.domain.repository.FavoriteRepository

class ToggleFavoriteUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(idea: Idea) = favoriteRepository.toggleFavorite(idea)
}