package com.dwiki.newsapp.network

import com.dwiki.newsapp.model.ResponseArticles
import com.dwiki.newsapp.model.ResponseSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines/sources")
    fun getAllSources(
        @Query("category") category : String,
        @Query("apiKey") apiKey : String = "289f6a94e12b4d68bea563e5b9dd57b3"
    ) : Call<ResponseSource>

    @GET("top-headlines")
    fun getAllArticles(
        @Query("sources") sources : String ,
        @Query("apikey") apikey : String = "289f6a94e12b4d68bea563e5b9dd57b3"
    ) : Call<ResponseArticles>
}