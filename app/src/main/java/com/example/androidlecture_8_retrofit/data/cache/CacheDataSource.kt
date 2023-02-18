package com.example.androidlecture_8_retrofit.data.cache

import com.example.androidlecture_8_retrofit.Joke
import com.example.androidlecture_8_retrofit.data.JokeCachedCallback
import com.example.androidlecture_8_retrofit.data.cloud.JokeServerModel

interface CacheDataSource {

    fun getJoke(jokeCachedCallback: JokeCachedCallback)

    fun addOrRemove(id: Int, joke: JokeServerModel): Joke
}

