package com.example.androidlecture_8_retrofit.data

import com.example.androidlecture_8_retrofit.data.JokeCallback


interface Model {
    fun getJoke()

    fun init(callback: JokeCallback)

    fun clear()
    fun changeJokeStatus(jokeCallback: JokeCallback)

    fun chooseDataSource(cached: Boolean)
}

