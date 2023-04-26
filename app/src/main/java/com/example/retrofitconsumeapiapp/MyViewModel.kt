package com.example.retrofitconsumeapiapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel : ViewModel() {
    private var _dogList = MutableLiveData<DogResponse>()
    val dogList: LiveData<DogResponse> = _dogList
    private val _dogBreed= MutableLiveData<String>()
    val dogBreed: LiveData<String> = _dogBreed

    fun getDogsByBreed() {
        CoroutineScope(Dispatchers.IO).launch {
            val endpoint = "images"
            val call = RetrofitHelper.getRetrofit().create(ApiService::class.java)
                .getDogsByBreed("${_dogBreed.value}" + "/" + endpoint)
            call.enqueue(object : Callback<DogResponse> {
                override fun onResponse(call: Call<DogResponse>, response: Response<DogResponse>) {
                    if (response.isSuccessful) {
                        _dogList.value = response.body()
                    } else {
                        //showError
                    }
                }

                override fun onFailure(call: Call<DogResponse>, t: Throwable) {
                    //showError
                }
            })
        }
    }

    fun onDogBreedChanged(newDogBreed:String){
        _dogBreed.value=newDogBreed.lowercase()
    }
}