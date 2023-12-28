package com.example.appsolarsystem.data.planets

import android.util.Log
import com.example.appsolarsystem.model.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePlanetRepository(private val planetList : MutableList<Planet>) : PlanetRepository {
    override fun getAllPlanetsStream(): Flow<List<Planet>>  = flow {

        Log.d("planetInFakeRepo", "${planetList}")
        emit(planetList)

    }

    override fun getPlanetStream(id: Int): Flow<Planet?> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshPlanets() {
        TODO("Not yet implemented")
    }


}