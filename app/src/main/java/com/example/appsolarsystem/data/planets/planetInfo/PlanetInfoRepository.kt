package com.example.appsolarsystem.data.planets.planetInfo

import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.PlanetInfo
import kotlinx.coroutines.flow.Flow
/**
 * Repository interface for retrieving and managing planet information.
 */
interface PlanetInfoRepository {

    /**
     * Get a flow of all planet information.
     *
     * @return A [Flow] emitting a list of [PlanetInfo] objects representing all available planet information.
     */
    fun getAllplanetInfosStream(): Flow<List<PlanetInfo>>

    /**
     * Get a flow of planet information associated with a specific planet.
     *
     * @param id The identifier of the planet for which information is requested.
     * @return A [Flow] emitting a list of [PlanetInfo] objects representing information about the specified planet.
     */
    fun getAllPlanetInfoByPlanetStream(id: Int): Flow<List<PlanetInfo?>>

    /**
     * Get a flow of planet information for a specific planet.
     *
     * @param id The identifier of the planet for which information is requested.
     * @return A [Flow] emitting a single [PlanetInfo] object representing information about the specified planet.
     */
    fun getPlanetInfoStream(id: Int): Flow<PlanetInfo?>

    /**
     * Refresh the planet information by fetching updated data from the remote data source.
     */
    suspend fun refreshPlanetInfos()
}
