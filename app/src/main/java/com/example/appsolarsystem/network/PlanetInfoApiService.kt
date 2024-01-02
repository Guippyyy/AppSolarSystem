package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.PlanetInfo
import retrofit2.http.GET
import retrofit2.http.Path
/**
 * Retrofit service interface for interacting with the PlanetInfo API.
 */
interface PlanetInfoApiService {

    /**
     * Retrieves a list of all planet information.
     *
     * @return A list of [PlanetInfo] objects representing information about planets.
     */
    @GET("planetInfo")
    suspend fun getPlanetInfo(): List<PlanetInfo>

    /**
     * Retrieves planet information for a specific planet.
     *
     * @param planetID The ID of the planet for which information is requested.
     * @return A list of [PlanetInfo] objects representing information about the specified planet.
     */
    @GET("planetInfo/planetID/{planetID}")
    suspend fun getPlanetInfoByPlanetId(@Path("planetID") planetID: Int): List<PlanetInfo>
}
