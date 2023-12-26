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
class OfflineFirstPlanetRepository(private val planetDAO: PlanetDAO, private val planetApi: PlanetApiService) : PlanetRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            updatePlanetsInBackground()
        }
    }

    private suspend fun updatePlanetsInBackground() {
        while (true) {
            refreshPlanets()
            delay(3000)
        }

    }

    override fun getAllPlanetsStream(): Flow<List<Planet>> {
        return planetDAO.getAllPlanets().map { planets -> planets.map { it.asDomainPlanet() } }
            .onEach {
                if (it.isEmpty()) {
                    refreshPlanets()
                }
            }
    }

    override fun getPlanetStream(id: Int): Flow<Planet?> {
        return planetDAO.getPlanet(id).map { planet -> planet?.asDomainPlanet() }
    }

    override suspend fun refreshPlanets() {
        planetApi.getPlanets()
            .also {
                externalPlanets -> planetDAO.deleteAndInsert(
                    planets = externalPlanets.map(Planet::asPlanetDatabase)
                )
            }
    }

}