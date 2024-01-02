package com.example.appsolarsystem.data.planets.planetInfo

import com.example.appsolarsystem.model.PlanetInfo
import com.example.appsolarsystem.network.PlanetInfoApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
/**
 * Repository implementation for PlanetInfo data that follows an offline-first approach.
 *
 * @param planetInfoDAO Data Access Object for PlanetInfo entities in the local database.
 * @param planetInfoApi API service for fetching PlanetInfo data from a remote source.
 */
class OfflineFirstPlanetInfoRepository(private val planetInfoDAO: PlanetInfoDAO, private val planetInfoApi: PlanetInfoApiService) :
    PlanetInfoRepository {

    /**
     * Initialize the repository by launching a coroutine to update planet infos in the background at regular intervals.
     */
    init {
        CoroutineScope(Dispatchers.IO).launch {
            updatePlanetInfosInBackground()
        }
    }

    /**
     * Continuously refresh planet infos in the background at regular intervals.
     */
    private suspend fun updatePlanetInfosInBackground() {
        while (true) {
            refreshPlanetInfos()
            delay(3000)
        }
    }

    /**
     * Get a [Flow] emitting a list of all planet infos.
     *
     * @return A [Flow] emitting a list of [PlanetInfo] objects.
     */
    override fun getAllplanetInfosStream(): Flow<List<PlanetInfo>> {
        return planetInfoDAO.getAllplanetInfo().map { p -> p.map { it.asDomainPlanetInfo() } }
            .onEach {
                if (it.isEmpty()) {
                    refreshPlanetInfos()
                }
            }
    }

    /**
     * Get a [Flow] emitting a list of planet infos associated with a specific planet.
     *
     * @param id The identifier of the planet.
     * @return A [Flow] emitting a list of nullable [PlanetInfo] objects.
     */
    override fun getAllPlanetInfoByPlanetStream(id: Int): Flow<List<PlanetInfo?>> {
        return planetInfoDAO.getPlanetInfoByPlanet(id).map { p -> p.map { it.asDomainPlanetInfo() } }
    }

    /**
     * Get a [Flow] emitting details of a specific planet info.
     *
     * @param id The identifier of the planet info.
     * @return A [Flow] emitting a nullable [PlanetInfo] object.
     */
    override fun getPlanetInfoStream(id: Int): Flow<PlanetInfo?> {
        return planetInfoDAO.getPlanetInfo(id).map { p -> p?.asDomainPlanetInfo() }
    }

    /**
     * Refresh planet info data by fetching it from the remote data source and updating the local database.
     */
    override suspend fun refreshPlanetInfos() {
        planetInfoApi.getPlanetInfo()
            .also { externalPlanetInfos ->
                planetInfoDAO.deleteAndInsert(
                    planetInfos = externalPlanetInfos.map(PlanetInfo::asPlanetInfoDatabase)
                )
            }
    }
}
