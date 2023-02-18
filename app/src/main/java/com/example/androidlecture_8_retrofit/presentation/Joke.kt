package com.example.androidlecture_8_retrofit

import androidx.annotation.DrawableRes
import com.example.androidlecture_8_retrofit.presentation.DataCallback


class BaseJoke(text: String, punchline: String) : Joke(text, punchline) {
    override fun getIconResId() = R.drawable.favorite_border_black_24dp

}


class FavoriteJoke(text: String, punchline: String) : Joke(text, punchline) {

    override fun getIconResId() = R.drawable.favorite_black_24dp

}

class FailedJoke(text: String) : Joke(text, "") {
    override fun getIconResId() = 0
}


abstract class Joke(private val text: String, private val punchline: String) {

    private fun getJokeUi() = "$text\n$punchline"

    @DrawableRes
    protected abstract fun getIconResId() : Int

    fun map(callback: DataCallback) = callback.run {
        provideText(getJokeUi())
        provideIconRes(getIconResId())
    }
}
