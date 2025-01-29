package com.example.ideasai.core.data.datasource

import com.example.ideasai.core.data.database.dao.IdeaDao
import com.example.ideasai.core.data.database.entity.IdeaEntity
import com.example.ideasai.core.data.database.entity.toDomain
import com.example.ideasai.core.data.database.entity.toEntity
import com.example.ideasai.core.data.model.Idea
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface FavoriteDataSource {
    fun insertFavorites(ideas: List<Idea>)
    fun getFavorites(): Flow<List<Idea>>
    fun getFavoriteIds(): Flow<List<String>>
    fun toggleFavorite(idea: Idea)
}

class FavoriteDataSourceImpl @Inject constructor(
    private val ideaDao: IdeaDao
) : FavoriteDataSource {
    override fun insertFavorites(ideas: List<Idea>) {
        ideaDao.insert(ideas.map(Idea::toEntity))
    }

    override fun getFavorites(): Flow<List<Idea>> =
        ideaDao.getFavoriteIdeas().map { it.map(IdeaEntity::toDomain) }

    override fun getFavoriteIds(): Flow<List<String>> =
        ideaDao.getFavoriteIdeas().map { it.map { it.id } }

    override fun toggleFavorite(idea: Idea) {
        ideaDao.update(idea.toEntity().copy(isFavorite = !idea.isFavorite))
    }
}
