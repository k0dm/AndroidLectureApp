package com.example.androidlecture_8_retrofit.data.cache

import com.example.androidlecture_8_retrofit.JokeUiModel
import com.example.androidlecture_8_retrofit.data.Joke
import com.example.androidlecture_8_retrofit.data.cache.realm.JokeRealm
import com.example.androidlecture_8_retrofit.data.cache.realm.RealmProvider
import com.example.androidlecture_8_retrofit.data.cloud.Result
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BaseCachedDataSource(private val realmProvider: RealmProvider) : CacheDataSource {
    override suspend fun getJoke(): Result<Joke, Unit> {

        realmProvider.provide().use {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty()) {
                return Result.Error(Unit)
            } else {
                jokes.random().let { jokeRealm ->
                    return Result.Success(
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

    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                return@withContext if (jokeRealm == null) {
                    val newJoke = joke.toJokeRealm()

                    it.executeTransaction { transition ->
                        transition.insert(newJoke)
                    }
                    joke.toFavoriteJoke()
                } else {
                    it.executeTransaction {
                        jokeRealm.deleteFromRealm()
                    }
                    joke.toBaseJoke()
                }
            }
        }

}