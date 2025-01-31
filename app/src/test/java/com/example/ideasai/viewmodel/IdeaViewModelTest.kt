package com.example.ideasai.viewmodel

import app.cash.turbine.test
import com.example.ideasai.core.domain.DomainState
import com.example.ideasai.core.domain.repository.FavoriteRepository
import com.example.ideasai.core.domain.repository.IdeaRepository
import com.example.ideasai.core.domain.usecase.GenerateIdeasUseCase
import com.example.ideasai.core.domain.usecase.ToggleFavoriteUseCase
import com.example.ideasai.core.testing.data.TestData
import com.example.ideasai.core.testing.util.MainDispatcherRule
import com.example.ideasai.feature.idea.IdeaUiState
import com.example.ideasai.feature.idea.IdeaViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class IdeaViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var ideaRepository: IdeaRepository

    @Mock
    lateinit var favoriteRepository: FavoriteRepository

    private lateinit var generateIdeasUseCase: GenerateIdeasUseCase

    private lateinit var toggleFaUseCase: ToggleFavoriteUseCase

    private lateinit var viewModel: IdeaViewModel

    @Before
    fun setup() {
        generateIdeasUseCase = GenerateIdeasUseCase(ideaRepository)
        toggleFaUseCase = ToggleFavoriteUseCase(favoriteRepository)
        viewModel = IdeaViewModel(generateIdeasUseCase, toggleFaUseCase)
    }

    @Test
    fun `when generate ideas is called and repository returns error, should update ui state from Loading to Error`() = runTest {
        // Arrange
        val expectedIdeas = TestData.errorMessage
        val testFlow = flow {
            emit(DomainState.Loading)
            delay(100)
            emit(DomainState.Error(expectedIdeas))
        }

        whenever(ideaRepository.generate(
            TestData.about,
            TestData.purpose,
            TestData.topic,
            TestData.language
        )).thenReturn(testFlow)

        // Act & Assert
        viewModel.uiState.test {
            // Initial state
            assertEquals(IdeaUiState.Initial, awaitItem())

            // Trigger action
            viewModel.generate(
                TestData.about,
                TestData.purpose,
                TestData.topic,
                TestData.language
            )

            // Verify state transitions
            assertEquals(IdeaUiState.Loading, awaitItem())
            val errorState = awaitItem() as IdeaUiState.Error
            assertEquals(expectedIdeas, errorState.message)

            // Ensure no more emissions
            cancelAndIgnoreRemainingEvents()
        }

        // Verify repository call
        verify(ideaRepository).generate(
            TestData.about,
            TestData.purpose,
            TestData.topic,
            TestData.language
        )
    }

    @Test
    fun `when generate ideas is called and repository returns success, should update ui state from Loading to Success`() = runTest {
        // Arrange
        val expectedIdeas = TestData.ideas
        val testFlow = flow {
            emit(DomainState.Loading)
            delay(100)
            emit(DomainState.Success(expectedIdeas))
        }

        whenever(ideaRepository.generate(
            TestData.about,
            TestData.purpose,
            TestData.topic,
            TestData.language
        )).thenReturn(testFlow)

        // Act & Assert
        viewModel.uiState.test {
            // Initial state
            assertEquals(IdeaUiState.Initial, awaitItem())

            // Trigger action
            viewModel.generate(
                TestData.about,
                TestData.purpose,
                TestData.topic,
                TestData.language
            )

            // Verify state transitions
            assertEquals(IdeaUiState.Loading, awaitItem())
            val successState = awaitItem() as IdeaUiState.Success
            assertEquals(expectedIdeas, successState.data)

            // Ensure no more emissions
            cancelAndIgnoreRemainingEvents()
        }

        // Verify repository call
        verify(ideaRepository).generate(
            TestData.about,
            TestData.purpose,
            TestData.topic,
            TestData.language
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggleFavorite should call use case with correct idea`() = runTest {
        // Given
        val testIdea = TestData.ideas.first()

        // When
        viewModel.toggleFavorite(testIdea)

        // Wait for coroutine to complete
        advanceUntilIdle()

        // Then
        verify(favoriteRepository).toggleFavorite(testIdea)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggleFavorite should handle multiple calls sequentially`() = runTest {
        // Given
        val idea1 = TestData.ideas[0]
        val idea2 = TestData.ideas[1]

        // When
        viewModel.toggleFavorite(idea1)
        viewModel.toggleFavorite(idea2)
        advanceUntilIdle()

        // Then
        val inOrder = inOrder(favoriteRepository)
        inOrder.verify(favoriteRepository).toggleFavorite(idea1)
        inOrder.verify(favoriteRepository).toggleFavorite(idea2)
    }
}