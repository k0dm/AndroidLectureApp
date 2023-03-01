package com.example.androidlecture_8_retrofit.presentation

import androidx.annotation.DrawableRes
import com.example.androidlecture_8_retrofit.Joke
import com.example.androidlecture_8_retrofit.data.Model
import com.example.androidlecture_8_retrofit.data.JokeCallback


class ViewModel(private val model: Model) {

    private var dataCallback: DataCallback? = null

    private val jokeCallback = object : JokeCallback {
        override fun provide(joke: Joke) {
            dataCallback?.let {
                joke.map(it)
            }
        }

    }


    fun init(callback: DataCallback) {
        this.dataCallback = callback

        model.init(jokeCallback)
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        dataCallback = null
        model.clear()
    }

    fun changeJokeStatus() {
        Thread {
            model.changeJokeStatus(jokeCallback)
        }.start()
    }


    fun choseFavorites(checked: Boolean) {
        model.chooseDataSource(checked)
    }
}

interface DataCallback {
    fun provideText(text: String)

    fun provideIconRes(@DrawableRes id: Int)
}

