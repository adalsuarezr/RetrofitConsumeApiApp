package com.example.retrofitconsumeapiapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper{
    fun getRetrofit(): Retrofit {
        val baseUrl="https://dog.ceo/api/breed/" //url from the api we want to cosume
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()) //Converter factory to Gson
            .build()
    }
}