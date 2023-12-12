package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.Moon
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    //"http://192.168.100.101:3000/"
    "http://192.168.54.15:3000"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()

interface MoonApiService {
    @GET("moon")
    suspend fun getMoons(): List<Moon>

    @GET("moons/{planetId}")
    suspend fun getMoonsByPlanetId(@Path("planetId") planetId: Int): List<Moon>
}

object MoonApi {
    val retrofitService: MoonApiService by lazy {
        retrofit.create(MoonApiService::class.java)
    }
}