package com.example.ideasai.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdeaResponse(
    @SerialName("ideas")
    val ideas: List<Idea> = emptyList()
)