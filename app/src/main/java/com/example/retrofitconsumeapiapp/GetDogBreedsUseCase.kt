package com.example.retrofitconsumeapiapp

import retrofit2.awaitResponse

class GetDogBreedsUseCase(private val apiService: ApiService) {
    suspend fun execute(): DogBreedsResponse? {
        val endpoint = "breeds/list/all"
        val response = apiService.getDogBreeds(endpoint).awaitResponse()
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}