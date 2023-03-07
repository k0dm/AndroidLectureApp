package com.example.androidlecture_8_retrofit.data.cache

import com.example.androidlecture_8_retrofit.JokeUiModel
import com.example.androidlecture_8_retrofit.data.Joke
import com.example.androidlecture_8_retrofit.data.cloud.Result

//class TestCacheDataSource : CacheDataSource {
//
//    private val list = ArrayList<Pair<Int, Joke>>()
//    override suspend fun getJoke(): Result<Any, Unit> {
//        if (list.isEmpty()) {
//            jokeCachedCallback.fail()
//        }else {
//            jokeCachedCallback.provide(list.random().second)
//        }
//    }
//
//    override fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
//        val found = list.find { it.first == id }
//        return if (found != null){
//            val joke = found.second.toBaseJoke()
//            list.remove(found)
//            joke
//        } else {
//            list.add(Pair(id,joke))
//            joke.toFavoriteJoke()
//        }
//    }
//}