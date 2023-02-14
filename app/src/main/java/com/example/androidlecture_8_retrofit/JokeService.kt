package com.example.androidlecture_8_retrofit

import retrofit2.Call
import retrofit2.http.GET

interface JokeService {
    @GET("https://official-joke-api.appspot.com/random_joke/")
    fun getJoke(): Call<JokeDTO>
}

interface ServiceCallback {
    fun returnSuccess(data: JokeDTO)
    fun returnError(type: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    OTHER
}