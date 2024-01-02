package com.example.appsolarsystem.data.planets

import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.network.PlanetApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
/**
 * Repository for handling planets with an offline-first approach.
 *
 * @param planetDAO The Data Access Object (DAO) for planets.
 * @param planetApi The API service for fetching planet data from a remote source.
 */
class OfflineFirstPlanetRepository(private val planetDAO: PlanetDAO, private val planetApi: PlanetApiService) : PlanetRepository {

    /**
     * Initialize the repository by launching a coroutine to update planets in the background.
     */
    init {
        CoroutineScope(Dispatchers.IO).launch {
            updatePlanetsInBackground()
        }
    }

    /**
     * Periodically updates planets in the background at a fixed interval.
     */
    private suspend fun updatePlanetsInBackground() {
        while (true) {
            refreshPlanets()
            delay(3000)
        }
    }

    /**
     * Get a flow of all planets.
     *
     * @return A [Flow] emitting a list of [Planet] objects representing all available planets.
     */
    override fun getAllPlanetsStream(): Flow<List<Planet>> {
        return planetDAO.getAllPlanets().map { planets -> planets.map { it.asDomainPlanet() } }
            .onEach {
                if (it.isEmpty()) {
                    refreshPlanets()
                }
            }
    }

    /**
     * Get a flow of a specific planet.
     *
     * @param id The identifier of the planet for which data is requested.
     * @return A [Flow] emitting a single [Planet] object representing the specified planet.
     */
    override fun getPlanetStream(id: Int): Flow<Planet?> {
        return planetDAO.getPlanet(id).map { planet -> planet?.asDomainPlanet() }
    }

    /**
     * Refresh the planets by fetching updated data from the remote data source.
     */
    override suspend fun refreshPlanets() {
        planetApi.getPlanets()
            .also {
                    externalPlanets -> planetDAO.deleteAndInsert(
                planets = externalPlanets.map(Planet::asPlanetDatabase)
            )
            }
    }
}
