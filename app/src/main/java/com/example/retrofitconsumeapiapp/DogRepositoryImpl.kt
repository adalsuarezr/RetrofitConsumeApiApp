package com.example.retrofitconsumeapiapp

class DogRepositoryImpl(private val apiService: ApiService) : DogRepository {
    override suspend fun getDogBreeds(): DogBreedsResponse? {
        return GetDogBreedsUseCase(apiService).execute()
    }

    override suspend fun getDogsByBreed(dogBreed: String): DogImagesResponse? {
        return GetDogsByBreedUseCase(apiService).execute(dogBreed)
    }
}