package com.example.myphone.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    fun getDataFromApi(): Call<Phone>


    @GET("details/{id}")
    fun getDataFromDeta(): Call<DetailsList>
}