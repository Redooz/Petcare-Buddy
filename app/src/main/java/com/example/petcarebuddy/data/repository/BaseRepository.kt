package com.example.petcarebuddy.data.repository

import com.example.petcarebuddy.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    suspend fun <T> safeApiCall(
        apiCall: suspend  () -> T
    ) : Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when(throwable) {
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    is Exception -> Resource.Wtf(throwable.message!!)
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }
}