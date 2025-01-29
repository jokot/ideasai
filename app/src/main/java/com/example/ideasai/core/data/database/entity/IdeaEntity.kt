package com.example.ideasai.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.ideasai.core.data.model.Idea

@Entity(tableName = "idea")
data class IdeaEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val topic: String,
    val isFavorite: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

fun IdeaEntity.toDomain() = Idea(
    id, name, topic, isFavorite
)

fun Idea.toEntity() = IdeaEntity(
    id, name, topic, isFavorite
)