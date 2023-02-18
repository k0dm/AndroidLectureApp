package com.example.androidlecture_8_retrofit.data.cloud

import com.example.androidlecture_8_retrofit.data.JokeCloudCallback

interface CloudDataSource {

    fun getJoke(callback: JokeCloudCallback)
}