package com.example.ideasai.core.domain.repository

import com.example.ideasai.core.data.datasource.FavoriteDataSource
import com.example.ideasai.core.data.model.Idea
import com.example.ideasai.core.data.database.IoDispatcher
import com.example.ideasai.core.domain.DomainState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface FavoriteRepository {
    fun getFavorites(): Flow<DomainState<List<Idea>>>
    suspend fun toggleFavorite(idea: Idea)
}

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteDataSource: FavoriteDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FavoriteRepository {

    override fun getFavorites(): Flow<DomainState<List<Idea>>> = flow {
        emit(DomainState.Loading)
        favoriteDataSource.getFavorites()
            .catch { e ->
                emit(DomainState.Error(e.localizedMessage.orEmpty()))
            }.collect {
                emit(DomainState.Success(it))
            }
    }.flowOn(dispatcher)

    override suspend fun toggleFavorite(idea: Idea) {
        withContext(dispatcher) {
            favoriteDataSource.toggleFavorite(idea)
        }
    }
}