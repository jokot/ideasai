package com.example.ideasai.di

import com.example.ideasai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerationConfig
import com.google.ai.client.generativeai.type.generationConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GenerativeAiModule {

    @Provides
    @Singleton
    fun provideGenerativeConfig(): GenerationConfig = generationConfig {
        temperature = 0.7f
    }

    @Provides
    @Singleton
    fun provideGenerativeModule(
        config: GenerationConfig
    ): GenerativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash-latest",
        apiKey = BuildConfig.apiKey,
        generationConfig = config
    )
}