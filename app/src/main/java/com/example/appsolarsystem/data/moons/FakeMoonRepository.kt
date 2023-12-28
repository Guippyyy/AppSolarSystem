package com.example.appsolarsystem.data.moons

import com.example.appsolarsystem.model.Moon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMoonRepository (private val moonList : MutableList<Moon>) : MoonRepository {
    override fun getAllMoonsStream(): Flow<List<Moon>> {
        TODO("Not yet implemented")
    }

    override fun getAllMoonsByPlanetStream(id: Int): Flow<List<Moon?>>  = flow {
        emit(moonList)
    }

    override fun getMoonStream(id: Int): Flow<Moon?> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshMoons() {
        TODO("Not yet implemented")
    }

}