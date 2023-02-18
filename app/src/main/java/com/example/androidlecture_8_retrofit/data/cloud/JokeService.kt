package com.example.androidlecture_8_retrofit.data.cloud

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke(): Call<JokeServerModel>
}

interface ServiceCallback {
    fun returnSuccess(data: JokeServerModel)
    fun returnError(type: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}