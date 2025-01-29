package com.example.ideasai.core.data.model

data class Idea(
    val id: String,
    val name: String,
    val topic: String,
    val isFavorite: Boolean = false
)
