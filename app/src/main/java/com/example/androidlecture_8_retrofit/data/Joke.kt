package com.example.androidlecture_8_retrofit.data

import com.example.androidlecture_8_retrofit.BaseJoke
import com.example.androidlecture_8_retrofit.FavoriteJoke
import com.example.androidlecture_8_retrofit.data.cache.CacheDataSource
import com.example.androidlecture_8_retrofit.data.cache.realm.JokeRealm

class Joke(
    private val id: Int,
    private val type:String,
    private val text:String,
    private val punchline:String
){
    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(id, this)
    fun toBaseJoke() = BaseJoke(text, punchline)
    fun toFavoriteJoke() = FavoriteJoke(text, punchline)
    fun toJokeRealm() = JokeRealm().also {
        it.id = id
        it.type = type
        it.text = text
        it.punchLine = punchline

    }


}