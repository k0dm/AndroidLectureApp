package com.example.androidlecture_8_retrofit

import android.app.Application
import com.example.androidlecture_8_retrofit.data.BaseModel
import com.example.androidlecture_8_retrofit.data.BaseResourceManager
import com.example.androidlecture_8_retrofit.data.cache.TestCacheDataSource
import com.example.androidlecture_8_retrofit.data.cloud.BaseCloudDataSource
import com.example.androidlecture_8_retrofit.data.cloud.JokeService
import com.example.androidlecture_8_retrofit.presentation.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {

    lateinit var viewModel: ViewModel
    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        viewModel = ViewModel(
            BaseModel(
                TestCacheDataSource(),
                BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                BaseResourceManager(this)
            )
        )
    }

}