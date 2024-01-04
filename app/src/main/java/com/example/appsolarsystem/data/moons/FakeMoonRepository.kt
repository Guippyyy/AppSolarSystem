package com.example.appsolarsystem.data.moons

import android.util.Log
import com.example.appsolarsystem.model.Moon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
/**
 * Fake implementation of [MoonRepository] for testing purposes.
 *
 * This repository provides a fake implementation of methods for accessing and manipulating Moon data.
 *
 * @property moonList The list of fake moons to be used for testing.
 */
class FakeMoonRepository(private val moonList: MutableList<Moon>) : MoonRepository {

    /**
     * Returns a flow of the list of moons.
     *
     * @return A flow emitting the list of moons.
     */
    override fun getAllMoonsStream(): Flow<List<Moon>> {
        TODO("Not yet implemented")
    }

    /**
     * Returns a flow of the list of moons associated with a specific planet ID.
     *
     * @param id The ID of the planet.
     * @return A flow emitting the list of moons for the specified planet ID.
     */
    override fun getAllMoonsByPlanetStream(id: Int): Flow<List<Moon?>> = flow{
        Log.d("moonRepo", "${moonList}")
        emit(moonList)
    }

    /**
     * Returns a flow of a single moon with the specified ID.
     *
     * @param id The ID of the moon.
     * @return A flow emitting the single moon with the specified ID.
     */
    override fun getMoonStream(id: Int): Flow<Moon?> {
        TODO("Not yet implemented")
    }

    /**
     * Refreshes the moons data (not implemented in the fake repository).
     */
    override suspend fun refreshMoons() {
        TODO("Not yet implemented")
    }
}
