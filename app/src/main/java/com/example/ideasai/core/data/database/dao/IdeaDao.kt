package com.example.ideasai.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ideasai.core.data.database.entity.IdeaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IdeaDao {

    @Insert
    fun insert(ideas: List<IdeaEntity>)

    @Update
    fun update(idea: IdeaEntity)

    @Query("SELECT * FROM idea WHERE isFavorite = 1")
    fun getFavoriteIdeas(): Flow<List<IdeaEntity>>

    @Query("SELECT id FROM idea WHERE isFavorite = 1")
    fun getFavoriteIdeaIds(): Flow<List<String>>
}