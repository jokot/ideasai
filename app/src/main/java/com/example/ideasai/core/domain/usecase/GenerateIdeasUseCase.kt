package com.example.ideasai.core.domain.usecase

import com.example.ideasai.core.domain.repository.IdeaRepository

class GenerateIdeasUseCase(
    private val ideaRepository: IdeaRepository
) {
    suspend operator fun invoke(
        about: String,
        purpose: String,
        topics: String,
        lang: String
    ) = ideaRepository.generate(about, purpose, topics, lang)
}