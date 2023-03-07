package com.example.androidlecture_8_retrofit

import android.app.Application
import com.example.androidlecture_8_retrofit.data.BaseModel
import com.example.androidlecture_8_retrofit.data.BaseResourceManager
import com.example.androidlecture_8_retrofit.data.cache.BaseCachedDataSource
import com.example.androidlecture_8_retrofit.data.cache.realm.RealmProvider
import com.example.androidlecture_8_retrofit.data.cloud.BaseCloudDataSource
import com.example.androidlecture_8_retrofit.data.cloud.JokeService
import com.example.androidlecture_8_retrofit.presentation.ViewModel
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JokeApp : Application() {

    lateinit var viewModel: ViewModel
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)


//        val appID : String = YOUR_APP_ID;
//        app = App(AppConfiguration.Builder(appID)
//            .build())
//        val config = SyncConfiguration.Builder()
//            .allowQueriesOnUiThread(true)
//            .allowWritesOnUiThread(true)
//            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        viewModel = ViewModel(
            BaseModel(
                BaseCachedDataSource(RealmProvider.Base()),
                BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                BaseResourceManager(this)
            )
        )
    }

}