package com.example.androidlecture_8_retrofit.data

import com.example.androidlecture_8_retrofit.JokeUiModel
import com.example.androidlecture_8_retrofit.data.JokeCallback


interface Model {
    suspend fun getJoke() : JokeUiModel

    fun init(callback: JokeCallback)

    fun clear()
    suspend fun changeJokeStatus(): JokeUiModel?

    fun chooseDataSource(cached: Boolean)
}

