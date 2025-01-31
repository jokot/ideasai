package com.example.ideasai

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.ideasai.core.testing.data.TestData
import com.example.ideasai.core.testing.data.TestTag
import com.example.ideasai.feature.idea.IdeaScreen
import com.example.ideasai.feature.idea.IdeaUiState
import org.junit.Rule
import org.junit.Test

class IdeaScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ideasAreDisplayed() {
        composeTestRule.setContent {
            IdeaScreen(
                uiState = IdeaUiState.Success(TestData.ideas),
                onGenerateClick = { _, _, _, _ -> },
                onToggleFavorite = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.IDEA_SCREEN)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(TestData.ideas.first().name)
            .assertExists()
    }

    @Test
    fun loadingStateAreDisplayed() {
        composeTestRule.setContent {
            IdeaScreen(
                uiState = IdeaUiState.Loading,
                onGenerateClick = { _, _, _, _ -> },
                onToggleFavorite = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.IDEA_SCREEN)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTag.LOADING_STATE)
            .assertIsDisplayed()
    }

    @Test
    fun errorStateAreDisplayed() {
        composeTestRule.setContent {
            IdeaScreen(
                uiState = IdeaUiState.Error(TestData.errorMessage),
                onGenerateClick = { _, _, _, _ -> },
                onToggleFavorite = {}
            )
        }

        composeTestRule
            .onNodeWithTag(TestTag.IDEA_SCREEN)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(TestTag.ERROR_STATE)
            .assertIsDisplayed()
    }
}
