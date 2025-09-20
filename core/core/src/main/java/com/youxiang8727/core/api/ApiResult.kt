package com.youxiang8727.core.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

sealed class ApiResult<out T> {
    data class Success<out T>(val data: T?) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()
}

suspend fun <T> fetchApi(
    apiCall: suspend () -> Response<T>
): ApiResult<T> {
    return try {
        val responseBody = withContext(Dispatchers.IO) {
            apiCall()
        }
        if (responseBody.isSuccessful) {
            ApiResult.Success(responseBody.body())
        } else {
            ApiResult.Error(Exception("api error(${responseBody.code()})"))
        }
    } catch (e: Exception) {
        e.printStackTrace()
        ApiResult.Error(e)
    }
}