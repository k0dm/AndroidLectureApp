package com.example.androidlecture_8_retrofit.data.cache

import com.example.androidlecture_8_retrofit.JokeUiModel
import com.example.androidlecture_8_retrofit.data.Joke
import com.example.androidlecture_8_retrofit.data.JokeCachedCallback
import com.example.androidlecture_8_retrofit.data.cache.realm.JokeRealm
import com.example.androidlecture_8_retrofit.data.cloud.JokeServerModel
import io.realm.Realm

class BaseCachedDataSource() : CacheDataSource {
    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        Realm.getDefaultInstance().use {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty()) {
                jokeCachedCallback.fail()
            } else {
                jokes.random().let {jokeRealm->
                    jokeCachedCallback.provide(
                        Joke(
                            jokeRealm.id,
                            jokeRealm.type,
                            jokeRealm.text,
                            jokeRealm.punchLine
                        )
                    )
                }
            }
        }
    }

    override fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
        Realm.getDefaultInstance().use {
            val jokeRealm = it.where(JokeRealm::class.java).equalTo("id",id).findFirst()
            return if (jokeRealm == null) {
                val newJoke = joke.toJokeRealm()

                it.executeTransaction{ transition ->
                    transition.insert(newJoke)
                }
                joke.toFavoriteJoke()
            } else{
                it.executeTransaction{
                    jokeRealm.deleteFromRealm()
                }
                joke.toBaseJoke()
            }
        }
    }
}