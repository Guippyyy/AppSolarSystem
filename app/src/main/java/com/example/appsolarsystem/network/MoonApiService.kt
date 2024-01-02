package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.Moon
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Retrofit service interface for interacting with the Moon API.
 */
interface MoonApiService {

    /**
     * Retrieves a list of all moons.
     *
     * @return A list of [Moon] objects representing moons in the solar system.
     */
    @GET("moon")
    suspend fun getMoons(): List<Moon>

    /**
     * Retrieves a list of moons associated with a specific planet.
     *
     * @param planetID The ID of the planet for which moons are requested.
     * @return A list of [Moon] objects representing moons associated with the specified planet.
     */
    @GET("moons/{planetID}")
    suspend fun getMoonsByPlanetId(@Path("planetID") planetID: Int): List<Moon>
}
