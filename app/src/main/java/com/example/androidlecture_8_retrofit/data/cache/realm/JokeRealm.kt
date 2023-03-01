package com.example.androidlecture_8_retrofit.data.cache.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class JokeRealm : RealmObject() {
    @PrimaryKey
    var id: Int = -1
    var text = ""
    var punchLine = ""
    var type = ""

}