package com.example.myphone.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("products")
    fun getDataFromApi(): Call<Phone>

    @GET("details/{id}")
    fun detailsPhone(@Path("id")id: Int): Call<details>
}