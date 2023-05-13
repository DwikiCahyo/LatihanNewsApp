package com.dwiki.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dwiki.newsapp.model.Article
import com.dwiki.newsapp.model.ResponseArticles
import com.dwiki.newsapp.network.ApiClient
import com.dwiki.newsapp.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val apiService: ApiService):ViewModel() {

    lateinit var liveDataArticle:MutableLiveData<List<Article>>

    init {
        liveDataArticle = MutableLiveData()
    }

    fun callApiArticle(source:String){
       apiService.getAllArticles(source)
            .enqueue(object:Callback<ResponseArticles>{
                override fun onResponse(
                    call: Call<ResponseArticles>,
                    response: Response<ResponseArticles>
                ) {
                   if (response.isSuccessful){
                       liveDataArticle.postValue(response.body()?.articles)
                       Log.d("ArticleViewModel","success")
                   } else{
                       liveDataArticle.postValue(null)
                       Log.e("ArticleViewModel","error")
                   }
                }

                override fun onFailure(call: Call<ResponseArticles>, t: Throwable){
                    liveDataArticle.postValue(null)
                    Log.e("ArticleViewModel","error")
                }

            })
    }
}