package com.example.androidlecture_8_retrofit.data

import com.example.androidlecture_8_retrofit.data.cloud.Result
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
    private val noCachedJoke by lazy { NoCachedJokes(resourceManager) }

    private var jokeCallback: JokeCallback? = null
    private var cachedJoke: Joke? = null


    private var getJokeFromCache = false

    override suspend fun getJoke(): JokeUiModel {

        if (getJokeFromCache) {
            return when (val result = cacheDataSource.getJoke()) {
                is Result.Success<Joke> -> result.data.let {
                    cachedJoke = it
                    it.toFavoriteJoke()
                }
                is Result.Error -> {
                    cachedJoke = null
                    FailedJoke(noCachedJoke.getMessage())
                }
            }

        } else {
            return when (val result = cloudDataSource.getJoke()) {
                is Result.Success<JokeServerModel> -> {
                    result.data.toJoke().let {
                        cachedJoke = it
                        it.toBaseJoke()
                    }
                }
                is Result.Error<ErrorType> -> {
                    cachedJoke = null
                    val failure = if (result.exception == ErrorType.NO_CONNECTION)
                        noConnection
                    else
                        serviceUnavailable
                    FailedJoke(failure.getMessage())
                }
            }
        }


    }

    override fun init(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun clear() {
        jokeCallback = null
    }

    override suspend fun changeJokeStatus(): JokeUiModel?  =
        cachedJoke?.change(cacheDataSource)


    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }
}