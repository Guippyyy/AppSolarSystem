package com.example.appsolarsystem.data.planets.planetInfo

import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.PlanetInfo
import kotlinx.coroutines.flow.Flow

interface PlanetInfoRepository {

    fun getAllplanetInfosStream() : Flow<List<PlanetInfo>>

    fun getAllPlanetInfoByPlanetStream(id : Int) : Flow<List<PlanetInfo?>>

    fun getPlanetInfoStream(id : Int ) : Flow<PlanetInfo?>
    suspend fun refreshPlanetInfos()
}