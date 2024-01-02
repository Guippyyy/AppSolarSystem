package com.example.appsolarsystem.data.planets

import android.util.Log
import com.example.appsolarsystem.model.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
/**
 * A fake implementation of [PlanetRepository] for testing purposes.
 *
 * @property planetList The list of planets to be used by the fake repository.
 */
class FakePlanetRepository(private val planetList: MutableList<Planet>) : PlanetRepository {

    /**
     * Get a flow of all planets.
     *
     * @return A [Flow] emitting a list of [Planet] objects representing all available planets.
     */
    override fun getAllPlanetsStream(): Flow<List<Planet>> = flow {
        Log.d("planetInFakeRepo", "${planetList}")
        emit(planetList)
    }

    /**
     * Get a flow of a specific planet.
     *
     * @param id The identifier of the planet for which data is requested.
     * @return A [Flow] emitting a single [Planet] object representing the specified planet.
     */
    override fun getPlanetStream(id: Int): Flow<Planet?> {
        // TODO: Implement this method based on the requirements.
        // For the fake implementation, it can be left unimplemented.
        TODO("Not yet implemented")
    }

    /**
     * Refresh the planets by fetching updated data from the remote data source.
     */
    override suspend fun refreshPlanets() {
        // TODO: Implement this method based on the requirements.
        // For the fake implementation, it can be left unimplemented.
        TODO("Not yet implemented")
    }
}
