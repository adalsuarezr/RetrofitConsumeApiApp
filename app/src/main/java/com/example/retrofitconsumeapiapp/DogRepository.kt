package com.example.retrofitconsumeapiapp

interface DogRepository {
    suspend fun getDogBreeds(): DogBreedsResponse?
    suspend fun getDogsByBreed(dogBreed: String): DogImagesResponse?
}