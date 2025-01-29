package com.example.ideasai.feature.idea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideasai.core.data.model.Idea
import com.example.ideasai.core.domain.DomainState
import com.example.ideasai.core.domain.usecase.GenerateIdeasUseCase
import com.example.ideasai.core.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IdeaViewModel @Inject constructor(
    private val generateIdeasUseCase: GenerateIdeasUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<IdeaUiState> =
        MutableStateFlow(IdeaUiState.Initial)
    val uiState: StateFlow<IdeaUiState> =
        _uiState.asStateFlow()

    fun generate(
        about: String,
        purpose: String,
        topics: String,
        lang: String
    ) {
        viewModelScope.launch {
            generateIdeasUseCase(
                about, purpose, topics, lang
            ).collect { domainState ->
                _uiState.update {
                    when (domainState) {
                        is DomainState.Loading -> IdeaUiState.Loading
                        is DomainState.Error -> IdeaUiState.Error(domainState.message)
                        is DomainState.Success -> IdeaUiState.Success(domainState.data)
                    }
                }
            }
        }
    }

    fun toggleFavorite(idea: Idea) {
        viewModelScope.launch {
            toggleFavoriteUseCase(idea)
        }
    }
}