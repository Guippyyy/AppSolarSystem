package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.Moon
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

private const val BASE_URL =
    "http://localhost:3000/"


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .build()

interface MoonApiService {
    @GET("moons")
    suspend fun getMoons(): List<Moon>
}

object MoonApi {
    val retrofitService: MoonApiService by lazy {
        retrofit.create(MoonApiService::class.java)
    }
}