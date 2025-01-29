package com.example.ideasai.feature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ideasai.core.data.model.Idea
import com.example.ideasai.core.domain.DomainState
import com.example.ideasai.core.domain.usecase.GetFavoritesUseCase
import com.example.ideasai.core.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<FavoriteUiState> =
        MutableStateFlow(FavoriteUiState.Initial)
    val uiState: StateFlow<FavoriteUiState> =
        _uiState.asStateFlow()

    init {
        getFavorite()
    }

    fun getFavorite() {
        viewModelScope.launch {
            getFavoritesUseCase().collect { domainState ->
                _uiState.update {
                    when (domainState) {
                        is DomainState.Loading -> FavoriteUiState.Loading
                        is DomainState.Error -> FavoriteUiState.Error(domainState.message)
                        is DomainState.Success -> if (domainState.data.isEmpty()) {
                            FavoriteUiState.Empty
                        } else {
                            FavoriteUiState.Success(domainState.data)
                        }
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