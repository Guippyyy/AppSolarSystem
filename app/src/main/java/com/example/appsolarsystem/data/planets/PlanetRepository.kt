package com.example.appsolarsystem.data.planets

import com.example.appsolarsystem.model.Planet
import kotlinx.coroutines.flow.Flow


/**
 * Repository interface for managing planet data. Provides methods for observing streams of planet data,
 * fetching a single planet by ID, and refreshing the planet data.
 */
interface PlanetRepository {

    /**
     * Observes a stream of all planets.
     *
     * @return A [Flow] emitting a list of [Planet] objects representing all planets.
     */
    fun getAllPlanetsStream(): Flow<List<Planet>>

    /**
     * Observes a stream of a specific planet identified by its ID.
     *
     * @param id The unique identifier of the planet.
     * @return A [Flow] emitting a single [Planet] object representing the specified planet.
     */
    fun getPlanetStream(id: Int): Flow<Planet?>

    /**
     * Refreshes the planet data, typically by fetching the latest data from a remote data source.
     * This operation is performed asynchronously.
     */
    suspend fun refreshPlanets()

}
