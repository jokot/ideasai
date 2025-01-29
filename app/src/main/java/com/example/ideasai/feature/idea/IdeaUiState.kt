package com.example.ideasai.feature.idea

import com.example.ideasai.core.data.model.Idea

sealed interface IdeaUiState {
    data object Initial : IdeaUiState
    data object Loading : IdeaUiState
    data class Error(val message: String) : IdeaUiState
    data class Success(val data: List<Idea>) : IdeaUiState
}