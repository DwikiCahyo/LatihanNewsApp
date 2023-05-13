package com.dwiki.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dwiki.newsapp.model.ResponseSource
import com.dwiki.newsapp.model.Source
import com.dwiki.newsapp.network.ApiClient
import com.dwiki.newsapp.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private val apiService: ApiService):ViewModel() {

    lateinit var liveDataSource:MutableLiveData<List<Source>>

    init {
        liveDataSource = MutableLiveData()
    }

    fun getDataSource():MutableLiveData<List<Source>>{
        return liveDataSource
    }

    fun callApiSource(category:String){
        ApiClient.instance.getAllSources(category)
            .enqueue(object :Callback<ResponseSource>{
                override fun onResponse(
                    call: Call<ResponseSource>,
                    response: Response<ResponseSource>
                ) {
                    if (response.isSuccessful){
                        liveDataSource.postValue(response.body()?.sources)
                        Log.d("SourceViewModel","success")
                    } else {
                        liveDataSource.postValue(null)
                        Log.e("SourceViewModel","error")
                    }
                }

                override fun onFailure(call: Call<ResponseSource>, t: Throwable) {
                    liveDataSource.postValue(null)
                    Log.e("SourceViewModel","$t")
                }

            })
    }
}