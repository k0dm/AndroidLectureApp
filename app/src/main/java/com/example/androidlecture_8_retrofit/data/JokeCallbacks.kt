package com.example.androidlecture_8_retrofit.data

import com.example.androidlecture_8_retrofit.data.cloud.ErrorType
import com.example.androidlecture_8_retrofit.Joke
import com.example.androidlecture_8_retrofit.data.cloud.JokeServerModel


interface JokeCallback {

    fun provide(joke: Joke)
}

interface JokeCloudCallback {

    fun provide(joke: JokeServerModel)

    fun fail(errorType: ErrorType)
}

interface JokeCachedCallback {

    fun provide(joke: JokeServerModel)

    fun fail()
}
