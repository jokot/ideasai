package com.example.ideasai.core.data.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class IdeaResponse(
    val id: String = UUID.randomUUID().toString(),
    val name: String? = null,
    val topic: String? = null
)

fun IdeaResponse.toDomain() =
    Idea(
        id = id,
        name = name.orEmpty(),
        topic = topic.orEmpty()
    )