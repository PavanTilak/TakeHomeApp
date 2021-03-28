package com.example.todolist.network

import com.example.todolist.api.IAwsToDoList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://mobile-tha-server.firebaseapp.com"

    //RxJava api call
    /*fun apiClient(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }*/

    //Coroutine api call
    fun makeRetrofitService(): IAwsToDoList {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(IAwsToDoList::class.java)
    }
}