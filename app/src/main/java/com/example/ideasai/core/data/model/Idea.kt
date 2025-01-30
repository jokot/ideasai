package com.example.ideasai.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.util.UUID

@Serializable
data class Idea(
    @Transient // Will not be serialized
    val id: String = UUID.randomUUID().toString(),

    @SerialName("name")
    val name: String,

    @SerialName("topic")
    val topic: String,

    @Transient // Will not be serialized
    val isFavorite: Boolean = false
)
