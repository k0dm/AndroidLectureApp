package com.example.androidlecture_8_retrofit.data.cache.realm

import io.realm.Realm

interface RealmProvider {
    fun provide(): Realm

    class Base : RealmProvider {
        override fun provide(): Realm = Realm.getDefaultInstance()

    }
}