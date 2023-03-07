package com.example.androidlecture_8_retrofit.data.cloud


interface CloudDataSource {

    suspend fun getJoke() : Result<JokeServerModel, ErrorType>
}