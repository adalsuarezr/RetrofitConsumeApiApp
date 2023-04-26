package com.example.retrofitconsumeapiapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getDogsByBreed(@Url url:String): Call<DogResponse>
}