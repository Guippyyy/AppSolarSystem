package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.Planet
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET



/**
 * Retrofit service interface for interacting with the Planet API.
 */
interface PlanetApiService {

    /**
     * Retrieves a list of all planets.
     *
     * @return A list of [Planet] objects representing planets in the solar system.
     */
    @GET("planet")
    suspend fun getPlanets(): List<Planet>
}
