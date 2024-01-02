package com.example.appsolarsystem.data.moons

import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.network.MoonApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

/**
 * Repository implementation for Moon data that follows an offline-first approach.
 *
 * @param moonDAO Data Access Object for Moon entities in the local database.
 * @param moonApi API service for fetching Moon data from a remote source.
 */
class OfflineFirstMoonRepository(private val moonDAO: MoonDAO, private val moonApi: MoonApiService) :
    MoonRepository {

    /**
     * Initialize the repository by launching a coroutine to update moons in the background at regular intervals.
     */
    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateMoonsInBackground()
        }
    }

    /**
     * Continuously refresh moons in the background at regular intervals.
     */
    private suspend fun updateMoonsInBackground() {
        while (true) {
            refreshMoons()
            delay(3000)
        }
    }

    /**
     * Get a [Flow] emitting a list of all moons.
     *
     * @return A [Flow] emitting a list of [Moon] objects.
     */
    override fun getAllMoonsStream(): Flow<List<Moon>> {
        return moonDAO.getAllMoons().map { moons -> moons.map { it.asDomainMoon() } }
            .onEach {
                if (it.isEmpty()) {
                    refreshMoons()
                }
            }
    }

    /**
     * Get a [Flow] emitting details of a specific moon.
     *
     * @param id The identifier of the moon.
     * @return A [Flow] emitting a nullable [Moon] object.
     */
    override fun getMoonStream(id: Int): Flow<Moon?> {
        return moonDAO.getMoon(id).map { moon -> moon?.asDomainMoon() }
    }

    /**
     * Get a [Flow] emitting a list of moons associated with a specific planet.
     *
     * @param id The identifier of the planet.
     * @return A [Flow] emitting a list of nullable [Moon] objects.
     */
    override fun getAllMoonsByPlanetStream(id: Int): Flow<List<Moon?>> {
        return moonDAO.getMoonsByPlanet(id).map { moon -> moon.map { it.asDomainMoon() } }
    }

    /**
     * Refresh moon data by fetching it from the remote data source and updating the local database.
     */
    override suspend fun refreshMoons() {
        moonApi.getMoons()
            .also { externalMoons ->
                moonDAO.deleteAndInsert(
                    moons = externalMoons.map(Moon::asMoonDatabase)
                )
            }
    }
}
