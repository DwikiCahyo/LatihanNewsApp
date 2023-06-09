package com.dwiki.newsapp.model


import com.google.gson.annotations.SerializedName

data class ResponseSource(
    @SerializedName("sources")
    val sources: List<Source>,
    @SerializedName("status")
    val status: String
)