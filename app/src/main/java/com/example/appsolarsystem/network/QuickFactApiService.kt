package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.QuickFact
import retrofit2.http.GET
import retrofit2.http.Path
/**
 * Retrofit service interface for interacting with the QuickFact API.
 */
interface QuickFactApiService {

    /**
     * Retrieves a list of all quick facts.
     *
     * @return A list of [QuickFact] objects representing quick facts about planets.
     */
    @GET("quickFacts")
    suspend fun getQuickFact(): List<QuickFact>

    /**
     * Retrieves quick facts for a specific planet.
     *
     * @param planetID The ID of the planet for which quick facts are requested.
     * @return A list of [QuickFact] objects representing quick facts about the specified planet.
     */
    @GET("quickFacts/planetID/{planetID}")
    suspend fun getQuickFactByPlanetId(@Path("planetID") planetID: Int): List<QuickFact>
}
