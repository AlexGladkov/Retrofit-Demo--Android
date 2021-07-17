package ru.agladkov.retrofitdemo.screens.profile.models

import com.google.gson.annotations.SerializedName

data class RetrofitCreateElementRequest(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("questId") val questId: String,
    @SerializedName("pageId") val pageId: String,
    @SerializedName("type") val type: String,
    @SerializedName("content") val content: String,
    @SerializedName("componentType") val componentType: Int
)