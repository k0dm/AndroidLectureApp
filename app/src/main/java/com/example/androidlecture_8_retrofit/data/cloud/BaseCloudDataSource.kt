package com.example.androidlecture_8_retrofit.data.cloud

import com.example.androidlecture_8_retrofit.data.JokeCloudCallback
import com.example.androidlecture_8_retrofit.data.cloud.Result
import retrofit2.Call
import retrofit2.Response
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService) : CloudDataSource {

    override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {

        return try {
            val result = service.getJoke()
            Result.Success(result)
        } catch (e: Exception) {
            val errorType = if (e is UnknownHostException)
                ErrorType.NO_CONNECTION
            else
                ErrorType.NO_CONNECTION
            Result.Error(errorType)
        }
    }
}