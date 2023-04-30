package com.example.retrofitconsumeapiapp

import com.google.gson.annotations.SerializedName

data class DogBreedsResponse(
    @SerializedName("message") val message: Map<String, List<String>>,
    @SerializedName("status")var status:String
)

