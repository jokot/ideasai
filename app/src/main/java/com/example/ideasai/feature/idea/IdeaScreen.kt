package com.example.ideasai.feature.idea

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ideasai.R
import com.example.ideasai.core.data.model.Idea
import com.example.ideasai.ui.component.ErrorCard
import com.example.ideasai.ui.component.IdeaItem
import com.example.ideasai.ui.component.IdeaScreenTopBar
import com.example.ideasai.ui.component.InputCard
import com.example.ideasai.ui.component.LoadingIndicator

@Composable
internal fun IdeaScreen(
    ideaViewModel: IdeaViewModel = hiltViewModel()
) {
    val ideaUiState by ideaViewModel.uiState.collectAsState()
    IdeaScreen(
        uiState = ideaUiState,
        onGenerateClick = ideaViewModel::generate,
        onToggleFavorite = ideaViewModel::toggleFavorite
    )
}

@Composable
fun IdeaScreen(
    uiState: IdeaUiState,
    onGenerateClick: (String, String, String, String) -> Unit,
    onToggleFavorite: (Idea) -> Unit
) {
    var about by rememberSaveable { mutableStateOf("") }
    var purpose by rememberSaveable { mutableStateOf("") }
    var topics by rememberSaveable { mutableStateOf("") }
    var lang by rememberSaveable { mutableStateOf("English") }

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
            item {
                InputCard(
                    about = about,
                    purpose = purpose,
                    topics = topics,
                    lang = lang,
                    onAboutChange = { about = it },
                    onPurposeChange = { purpose = it },
                    onTopicsChange = { topics = it },
                    onLangChange = { lang = it },
                    onGenerateClick = {
                        onGenerateClick(about, purpose, topics, lang)
                    }
                )
            }

            when (uiState) {
                IdeaUiState.Initial -> Unit
                IdeaUiState.Loading -> item { LoadingIndicator() }
                is IdeaUiState.Success -> {
                    items(uiState.data) { idea ->
                        IdeaItem(
                            idea = idea,
                            onToggleFavorite = { onToggleFavorite(idea) },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }

                is IdeaUiState.Error -> item {
                    ErrorCard(
                        message = uiState.message,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}