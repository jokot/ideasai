package com.example.ideasai.core.domain.usecase

import com.example.ideasai.core.domain.repository.FavoriteRepository

class GetFavoritesUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke() = favoriteRepository.getFavorites()
}