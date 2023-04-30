package com.example.retrofitconsumeapiapp

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel : ViewModel() {

    private val repository = DogRepositoryImpl(RetrofitHelper.apiService)

    private var _dogImageList = MutableLiveData<DogImagesResponse>()
    val dogImageList: LiveData<DogImagesResponse> = _dogImageList

    private val _dogBreed = MutableLiveData<String>()
    val dogBreed: LiveData<String> = _dogBreed

    private var _dogBreedsList = MutableLiveData<DogBreedsResponse>()
    val dogBreedsList: LiveData<DogBreedsResponse> = _dogBreedsList

    fun getDogBreedsList(context: Context) {
        viewModelScope.launch {
            val breeds = repository.getDogBreeds()
            if (breeds != null) {
                _dogBreedsList.value = breeds
            } else {
                Toast.makeText(context, "Error retrieving dog breeds", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getDogsByBreed(context: Context) {
        val dogBreed = dogBreed.value ?: return
        viewModelScope.launch {
            val dogs = repository.getDogsByBreed(dogBreed)
            if (dogs != null) {
                _dogImageList.value = dogs
            } else {
                Toast.makeText(context, "Error retrieving dogs by breed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onDogBreedChanged(newText: String) {
        _dogBreed.value = newText
    }

    fun onGoBackPress(){
        _dogImageList.value = DogImagesResponse("", emptyList())
    }
}