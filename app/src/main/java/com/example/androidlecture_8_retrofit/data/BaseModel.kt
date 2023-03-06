package com.example.androidlecture_8_retrofit.data

import com.example.androidlecture_8_retrofit.*
import com.example.androidlecture_8_retrofit.data.cache.CacheDataSource
import com.example.androidlecture_8_retrofit.data.cloud.CloudDataSource
import com.example.androidlecture_8_retrofit.data.cloud.ErrorType
import com.example.androidlecture_8_retrofit.data.cloud.JokeServerModel

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourceManager: BaseResourceManager
) : Model {
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    private val noCachedJoke by lazy {  NoCachedJokes(resourceManager) }

    private var jokeCallback: JokeCallback? = null
    private var cachedJoke: Joke? = null


    private var getJokeFromCache = false

    override fun getJoke() {

        if (getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCachedCallback {
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toFavoriteJoke())
                }

                override fun fail() {
                    cachedJoke = null
                    jokeCallback?.provide(FailedJoke(noCachedJoke.getMessage()))
                }

            })

        } else {
            cloudDataSource.getJoke(object : JokeCloudCallback {

                override fun provide(joke: Joke) {
                        cachedJoke = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }

                override fun fail(errorType: ErrorType) {
                    cachedJoke = null
                    val failure = if (errorType == ErrorType.NO_CONNECTION) {
                        noConnection
                    } else {
                        serviceUnavailable
                    }
                    jokeCallback?.provide(FailedJoke(failure.getMessage()))
                }

            })
        }
    }

    override fun init(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun clear() {
        jokeCallback = null
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback)  {
        cachedJoke?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }
}