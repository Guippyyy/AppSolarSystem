package com.example.appsolarsystem.data.planets.planetInfo

import com.example.appsolarsystem.model.PlanetInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePlanetInfoRepository (private val planetInfoList : MutableList<PlanetInfo>) : PlanetInfoRepository{
    override fun getAllplanetInfosStream(): Flow<List<PlanetInfo>> {
        TODO("Not yet implemented")
    }

    override fun getAllPlanetInfoByPlanetStream(id: Int): Flow<List<PlanetInfo?>> = flow  {
        emit(planetInfoList)
    }

    override fun getPlanetInfoStream(id: Int): Flow<PlanetInfo?> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshPlanetInfos() {
        TODO("Not yet implemented")
    }


}