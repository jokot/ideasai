package com.example.ideasai.feature.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ideasai.R
import com.example.ideasai.core.data.model.Idea
import com.example.ideasai.ui.component.ErrorCard
import com.example.ideasai.ui.component.FavoriteIdeaItem
import com.example.ideasai.ui.component.IdeaScreenTopBar
import com.example.ideasai.ui.component.LoadingIndicator

@Composable
fun FavoriteScreen(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val favoriteUiState by favoriteViewModel.uiState.collectAsState()
    FavoriteScreen(
        uiState = favoriteUiState,
        onToggleFavorite = favoriteViewModel::toggleFavorite
    )
}

@Composable
fun FavoriteScreen(
    uiState: FavoriteUiState,
    onToggleFavorite: (Idea) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        IdeaScreenTopBar(title = stringResource(R.string.idea_screen_title))
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            when (uiState) {
                is FavoriteUiState.Initial -> Unit
                is FavoriteUiState.Empty -> item { }
                is FavoriteUiState.Loading -> item { LoadingIndicator() }
                is FavoriteUiState.Success -> {
                    items(uiState.data) { idea ->
                        FavoriteIdeaItem(
                            idea = idea,
                            onToggleFavorite = { onToggleFavorite(idea) },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }

                is FavoriteUiState.Error -> item {
                    ErrorCard(
                        message = uiState.message,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}