package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.Planet
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "http://10.0.2.2:3000/"

private val json = Json {
    ignoreUnknownKeys = true
}

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()

interface PlanetApiService {
    @GET("planet")
    suspend fun getPlanets(): List<Planet>
}

object PlanetApi {
    val retrofitService: PlanetApiService by lazy {
        retrofit.create(PlanetApiService::class.java)
    }
}