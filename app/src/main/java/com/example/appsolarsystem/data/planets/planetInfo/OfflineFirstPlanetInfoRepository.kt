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

class OfflineFirstPlanetInfoRepository(private val planetInfoDAO : PlanetInfoDAO, private val planetInfoApi : PlanetInfoApiService ) : PlanetInfoRepository{
    init {
        CoroutineScope(Dispatchers.IO).launch {
            updatePlanetInfosInBackground()
        }
    }

    private suspend fun updatePlanetInfosInBackground() {
        while (true) {
            refreshPlanetInfos()
            delay(3000)
        }

    }

    override fun getAllplanetInfosStream(): Flow<List<PlanetInfo>> {

        return planetInfoDAO.getAllplanetInfo().map { p -> p.map { it.asDomainPlanetInfo() } }
            .onEach {
                if (it.isEmpty()){
                    refreshPlanetInfos()
                }
            }
    }

    override fun getAllPlanetInfoByPlanetStream(id: Int): Flow<List<PlanetInfo?>> {
        return planetInfoDAO.getPlanetInfoByPlanet(id).map { p -> p.map { it.asDomainPlanetInfo()}}
    }

    override fun getPlanetInfoStream(id: Int): Flow<PlanetInfo?> {
        return planetInfoDAO.getPlanetInfo(id).map { p -> p?.asDomainPlanetInfo()}
    }

    override suspend fun refreshPlanetInfos() {
        planetInfoApi.getPlanetInfo()
            .also {
                externalPlanetInfos -> planetInfoDAO.deleteAndInsert(
                    planetInfos = externalPlanetInfos.map(PlanetInfo::asPlanetInfoDatabase)
                )
            }
    }



}