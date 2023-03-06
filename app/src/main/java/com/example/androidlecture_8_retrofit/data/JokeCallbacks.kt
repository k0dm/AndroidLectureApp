package com.example.androidlecture_8_retrofit.data

import com.example.androidlecture_8_retrofit.data.cloud.ErrorType
import com.example.androidlecture_8_retrofit.JokeUiModel
import com.example.androidlecture_8_retrofit.data.cloud.JokeServerModel


interface JokeCallback {

    fun provide(jokeUiModel: JokeUiModel)
}

interface JokeCloudCallback {

    fun provide(joke: Joke)

    fun fail(errorType: ErrorType)
}

interface JokeCachedCallback {

    fun provide(joke: Joke)

    fun fail()
}
