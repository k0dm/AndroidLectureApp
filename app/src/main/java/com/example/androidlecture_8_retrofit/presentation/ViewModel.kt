package com.example.androidlecture_8_retrofit.presentation

import androidx.annotation.DrawableRes
import com.example.androidlecture_8_retrofit.JokeUiModel
import com.example.androidlecture_8_retrofit.data.Model
import com.example.androidlecture_8_retrofit.data.JokeCallback
import  androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModel(private val model: Model) : ViewModel() {

    private var dataCallback: DataCallback? = null

    private val jokeCallback = object : JokeCallback {
        override fun provide(jokeUiModel: JokeUiModel) {
            dataCallback?.let {
                jokeUiModel.map(it)
            }
        }

    }


    fun init(callback: DataCallback) {
        this.dataCallback = callback

        model.init(jokeCallback)
    }

    fun getJoke() = viewModelScope.launch {

        val uiModel = model.getJoke()

        dataCallback?.let {
            uiModel.map(it)
        }
    }


    fun clear() {
        dataCallback = null
        model.clear()
    }

     fun changeJokeStatus() = viewModelScope.launch{
        val uiModel = model.changeJokeStatus()
         dataCallback?.let {
             uiModel?.map(it)
         }
    }


    fun choseFavorites(checked: Boolean) {
        model.chooseDataSource(checked)
    }
}

interface DataCallback {
    fun provideText(text: String)

    fun provideIconRes(@DrawableRes id: Int)
}

