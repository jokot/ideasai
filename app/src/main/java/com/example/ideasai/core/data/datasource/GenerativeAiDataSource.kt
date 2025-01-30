package com.example.ideasai.core.data.datasource

import com.example.ideasai.core.data.DataResult
import com.example.ideasai.core.data.model.Idea
import com.example.ideasai.core.data.model.IdeaResponse
import com.example.ideasai.core.data.model.toDomain
import com.example.ideasai.core.util.cleanGeminiJsonResponse
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface GenerativeAiDataSource {
    suspend fun generate(
        about: String,
        purpose: String,
        topics: String,
        lang: String
    ): DataResult<List<Idea>>
}

class GenerativeAiDataSourceImpl @Inject constructor(
    private val generativeModel: GenerativeModel
) : GenerativeAiDataSource {
    override suspend fun generate(
        about: String,
        purpose: String,
        topics: String,
        lang: String
    ): DataResult<List<Idea>> {

        return try {
            val prompt =
                "I'm $about, Generate me a list of ideas for this purpose: $purpose, based on these topics: $topics, in this language: $lang. Make the output in this JSON format: $JSON_FORMAT"

            val response = generativeModel.generateContent(prompt)
            val json = Json {
                ignoreUnknownKeys = true
            }

            val ideas: List<Idea> = json.decodeFromString<List<IdeaResponse>>(
                response.text.orEmpty().cleanGeminiJsonResponse()
            ).map { it.toDomain() }

            DataResult.Success(ideas)
        } catch (e: Exception) {
            DataResult.Error(e.localizedMessage.orEmpty())
        }
    }

    companion object {
        const val JSON_FORMAT = "[\n" +
                "  {\n" +
                "    \"topic\": \"\",\n" +
                "    \"name\": \"\"\n" +
                "  }\n" +
                "]\n"
    }
}