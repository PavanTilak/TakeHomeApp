package com.example.todolist.api

import com.example.todolist.model.TakeHomeModel
import retrofit2.Call
import retrofit2.http.*

/**
URL to make Api Calls
https://mobile-tha-server.firebaseapp.com

Get Products List
/walmartproducts/{pageNumber}/{pageSize}
 */
interface IAwsToDoList {

    @GET("/walmartproducts/{pageNumber}/{pageSize}")
    fun getTakeHomeData(@Path("pageNumber") pageNumber: Int?, @Path("pageSize") pageSize: Int?): Call<TakeHomeModel>
}
