package com.example.retrofitconsumeapiapp

import retrofit2.*


class GetDogsByBreedUseCase(private val apiService: ApiService) {
    suspend fun execute(dogBreed: String): DogResponse? {
        val endpoint = "images"
        val response = apiService.getDogsByBreed("$dogBreed/$endpoint").awaitResponse()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}