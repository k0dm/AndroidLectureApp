package com.example.androidlecture_8_retrofit.data

import android.content.Context
import androidx.annotation.StringRes
import com.example.androidlecture_8_retrofit.R

interface JokeFailure {
    fun getMessage(): String
}

class NoConnection(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage(): String {
        return resourceManager.getString(R.string.no_connection)
    }
}

class ServiceUnavailable(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage(): String {
        return resourceManager.getString(R.string.service_unavailable)
    }
}

class NoCachedJokes(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage() = resourceManager.getString(R.string.no_cached_jokes)

}

interface ResourceManager {

    fun getString(@StringRes stringResId: Int): String
}

class BaseResourceManager(private val context: Context) : ResourceManager {
    override fun getString(stringResId: Int): String {
        return context.getString(stringResId)
    }
}