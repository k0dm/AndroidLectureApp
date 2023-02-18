package com.example.androidlecture_8_retrofit.data

import com.example.androidlecture_8_retrofit.BaseJoke
import com.example.androidlecture_8_retrofit.FailedJoke
import com.example.androidlecture_8_retrofit.FavoriteJoke

class TestModel(resourceManager: ResourceManager) : Model {
    private var callback: JokeCallback? = null
    private var count = 0
    private val noConnection = NoConnection(resourceManager)
    private val serviceUnavailable = ServiceUnavailable(resourceManager)


    override fun getJoke() {
        Thread {
            Thread.sleep(1000)
            when (count) {
                0 -> callback?.provide(BaseJoke("testText","testPunchline"))
                1 -> callback?.provide(FavoriteJoke("favoriteJokeText", "favorite joke punchLine"))
                2 -> callback?.provide(FailedJoke(serviceUnavailable.getMessage()))
            }

            count++
        }.start()
        if (count == 3) count = 0
    }

    override fun init(callback: JokeCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
    }

    override fun chooseDataSource(cached: Boolean) {
        TODO("Not yet implemented")
    }

}
