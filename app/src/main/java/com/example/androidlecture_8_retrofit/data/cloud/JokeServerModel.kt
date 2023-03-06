package com.example.androidlecture_8_retrofit.data.cloud

import com.example.androidlecture_8_retrofit.BaseJoke
import com.example.androidlecture_8_retrofit.FavoriteJoke
import com.example.androidlecture_8_retrofit.data.Joke
import com.example.androidlecture_8_retrofit.data.cache.CacheDataSource
import com.example.androidlecture_8_retrofit.data.cache.realm.JokeRealm
import com.google.gson.annotations.SerializedName

data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val text: String,
    @SerializedName("punchline")
    private val punchline: String,

    ) {
    fun toJoke() = Joke(id,type,text,punchline)

}
