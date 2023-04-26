package com.example.weatherapp

sealed class CallResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : CallResult<T>()
    data class Error(val message: String?, val statusCode: Int? = null) : CallResult<Nothing>()
}