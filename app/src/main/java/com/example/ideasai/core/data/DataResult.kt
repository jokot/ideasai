package com.example.ideasai.core.data

sealed interface DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>
    data class Error(val message: String) : DataResult<Nothing>
}
