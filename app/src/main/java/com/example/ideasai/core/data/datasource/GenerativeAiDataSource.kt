package com.example.ideasai.core.data.datasource

import android.util.Log
import com.example.ideasai.core.data.DataResult
import com.example.ideasai.core.data.model.Idea
import com.example.ideasai.core.data.model.IdeaResponse
import com.example.ideasai.core.data.model.toDomain
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
        val jsonFormat = "[\n" +
                "  {\n" +
                "    \"topic\": \"\",\n" +
                "    \"name\": \"\"\n" +
                "  }\n" +
                "]\n"

        return try {
            val prompt =
                "I'm $about, Generate me a list of ideas for this purpose: $purpose, based on these topics, in $lang: $topics. Make the output in this JSON format: $jsonFormat"

            val response = generativeModel.generateContent(prompt)
            var text = response.text.orEmpty()
            Log.d("generate_before:", "$text")
            text = text.replace("```json", "").replace("```", "")
            Log.d("generate_after:", "$text")
            val json = Json {
                ignoreUnknownKeys = true
            }
            val ideas: List<Idea> = json.decodeFromString<List<IdeaResponse>>(text)
                    .map { it.toDomain() }
            DataResult.Success(ideas)
        } catch (e: Exception) {
            DataResult.Error(e.localizedMessage.orEmpty())
        }
    }
}