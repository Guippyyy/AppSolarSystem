package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.Planet
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET



interface PlanetApiService {
    @GET("planet")
    suspend fun getPlanets(): List<Planet>
}

