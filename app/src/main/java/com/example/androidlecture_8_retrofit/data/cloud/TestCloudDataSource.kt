package com.example.androidlecture_8_retrofit.data.cloud

import com.example.androidlecture_8_retrofit.data.JokeCloudCallback

class TestCloudDataSource : CloudDataSource {
    private var count = 0

    override fun getJoke(callback: JokeCloudCallback) {
        callback.provide(
            JokeServerModel(
                0,
                "testType",
                "TestText$count",
                "TestPunchline$count"
            )
        )
        count++
    }
}