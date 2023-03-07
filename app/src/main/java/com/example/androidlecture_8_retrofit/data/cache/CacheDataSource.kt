package com.example.androidlecture_8_retrofit.data.cache

import com.example.androidlecture_8_retrofit.JokeUiModel
import com.example.androidlecture_8_retrofit.data.Joke
import com.example.androidlecture_8_retrofit.data.cloud.Result


interface CacheDataSource {

    suspend  fun getJoke(): Result<Joke, Unit>

    suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel
}

