package com.example.retrofitconsumeapiapp

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyViewModel : ViewModel() {
    private var _dogList = MutableLiveData<DogResponse>()
    val dogList: LiveData<DogResponse> = _dogList
    private val _dogBreed = MutableLiveData<String>()
    val dogBreed: LiveData<String> = _dogBreed

    private val apiService = RetrofitHelper.getRetrofit().create(ApiService::class.java)
    private val getDogsByBreedUseCase = GetDogsByBreedUseCase(apiService)

    fun getDogsByBreed(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getDogsByBreedUseCase.execute(_dogBreed.value ?: "")
            if (result != null) {
                _dogList.postValue(result)
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Something has gone wrong, try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    fun onDogBreedChanged(newDogBreed: String) {
        _dogBreed.value = newDogBreed.lowercase()
    }
}