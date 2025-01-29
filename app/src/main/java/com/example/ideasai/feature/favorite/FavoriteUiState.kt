package com.example.ideasai.feature.favorite

import com.example.ideasai.core.data.model.Idea

sealed interface FavoriteUiState {
    data object Initial : FavoriteUiState
    data object Loading : FavoriteUiState
    data object Empty: FavoriteUiState
    data class Error(val message: String) : FavoriteUiState
    data class Success(val data: List<Idea>) : FavoriteUiState
}