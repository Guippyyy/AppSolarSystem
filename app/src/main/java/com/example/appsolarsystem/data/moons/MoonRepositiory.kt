package com.example.appsolarsystem.data.moons

import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.Planet
import kotlinx.coroutines.flow.Flow
/**
 * Repository interface for accessing Moon data.
 */
interface MoonRepository {

    /**
     * Get a [Flow] emitting a list of all moons.
     *
     * @return A [Flow] emitting a list of [Moon] objects.
     */
    fun getAllMoonsStream(): Flow<List<Moon>>

    /**
     * Get a [Flow] emitting a list of moons associated with a specific planet.
     *
     * @param id The identifier of the planet.
     * @return A [Flow] emitting a list of nullable [Moon] objects.
     */
    fun getAllMoonsByPlanetStream(id: Int): Flow<List<Moon?>>

    /**
     * Get a [Flow] emitting details of a specific moon.
     *
     * @param id The identifier of the moon.
     * @return A [Flow] emitting a nullable [Moon] object.
     */
    fun getMoonStream(id: Int): Flow<Moon?>

    /**
     * Refresh moon data, typically by fetching updated data from a remote data source.
     */
    suspend fun refreshMoons()
}
