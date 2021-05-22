package ru.agladkov.retrofitdemo.network

import retrofit2.http.GET

interface DotaApi {

    @GET("./heroes")
    suspend fun fetchHeroes(): List<RemoteDotaHero>
}