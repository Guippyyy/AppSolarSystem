package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.Moon
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path


interface MoonApiService {
    @GET("moon")
    suspend fun getMoons(): List<Moon>

    @GET("moons/{planetID}")
    suspend fun getMoonsByPlanetId(@Path("planetID") planetID: Int): List<Moon>
}

