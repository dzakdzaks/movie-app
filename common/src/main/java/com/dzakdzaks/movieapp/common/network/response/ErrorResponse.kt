package com.dzakdzaks.movieapp.common.network.response

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NoInternetError : WrapperResponse.Error(message = "No Internet")

object TimeOutError : WrapperResponse.Error(message = "Time Out")

fun Exception.toError(): WrapperResponse.Error {
    return try {
        when {
            // when the exception is IOException and message NoInternet
            // set error as NoInternetError
            this is IOException && message == "No Internet" -> WrapperResponse.Error(message = "No Internet")

            //when the exception is HttpException
            this is HttpException -> {

                // parse the error body to RawHttpError data class
                val error = Gson().fromJson(
                    response()?.errorBody()?.string().orEmpty(),
                    RawHttpError::class.java,
                )

                // set error to HttpError with the message from error.error property or HTTP status message if it's null
                // set error code from the response HTTP Status Code or 400 if it's null
                // set data to null
                WrapperResponse.Error(
                    message = error.statusMessage ?: message(),
                    meta = mapOf(
                        "success" to error?.success,
                        "statusCode" to error?.statusCode,
                        "statusMessage" to error?.statusMessage
                    )
                )
            }
            // when the exception is UnknownHostException, set error to NoInternetError
            this is UnknownHostException -> {
                NoInternetError
            }
            // when the exception is SocketTimeoutException, set error to TimeOutError
            this is SocketTimeoutException -> {
                TimeOutError
            }
            // else set to default Error
            else -> {
                WrapperResponse.Error(message = message.orEmpty())
            }
        }
    } catch (e: Exception) {
        WrapperResponse.Error(message = e.message.orEmpty())
    }
}