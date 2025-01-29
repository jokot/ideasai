package com.example.ideasai.core.util

/**
 * Cleans Gemini API responses by removing Markdown code block wrappers (```json and ```).
 */
fun String.cleanGeminiJsonResponse(): String {
    val codeBlockRegex = """(?is)^\s*```json(.*?)```\s*${'$'}""".toRegex()
    return this.replace(codeBlockRegex, "$1").trim()
}