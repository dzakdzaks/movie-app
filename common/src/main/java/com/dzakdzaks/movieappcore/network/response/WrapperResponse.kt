package com.dzakdzaks.movieappcore.network.response

sealed class WrapperResponse<out T> private constructor() {
    class Success<T>(
        val data: T,
        val meta: Map<String, Any?> = mapOf(),
    ) : WrapperResponse<T>()

    open class Error(
        open val message: String,
        val meta: Map<String, Any?> = mapOf(),
    ) : WrapperResponse<Nothing>()

    object Empty : WrapperResponse<Nothing>()
}