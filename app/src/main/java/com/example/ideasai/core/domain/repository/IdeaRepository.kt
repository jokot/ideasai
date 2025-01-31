package com.example.ideasai.core.domain.repository

import com.example.ideasai.core.data.DataResult
import com.example.ideasai.core.data.database.IoDispatcher
import com.example.ideasai.core.data.datasource.FavoriteDataSource
import com.example.ideasai.core.data.datasource.GenerativeAiDataSource
import com.example.ideasai.core.data.datasource.HistoryDataSource
import com.example.ideasai.core.data.model.Idea
import com.example.ideasai.core.domain.DomainState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface IdeaRepository {
    suspend fun generate(
        about: String,
        purpose: String,
        topics: String,
        lang: String
    ): Flow<DomainState<List<Idea>>>
}

class IdeaRepositoryImpl @Inject constructor(
    private val generativeAiDataSource: GenerativeAiDataSource,
    private val favoriteDataSource: FavoriteDataSource,
    private val historyDataSource: HistoryDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : IdeaRepository {
    override suspend fun generate(
        about: String,
        purpose: String,
        topics: String,
        lang: String
    ): Flow<DomainState<List<Idea>>> = flow {
        emit(DomainState.Loading)
        when (
            val result = generativeAiDataSource.generate(
                about, purpose, topics, lang
            )
        ) {
            is DataResult.Success -> {
                historyDataSource.insertHistory(result.data)
                favoriteDataSource.getFavoriteIds()
                    .map { ids ->
                        DomainState.Success(
                            result.data.map { it.copy(isFavorite = it.id in ids) }
                        )
                    }
                    .collect { emit(it) }
            }

            is DataResult.Error -> emit(DomainState.Error(result.message))
        }
    }.flowOn(dispatcher)

}