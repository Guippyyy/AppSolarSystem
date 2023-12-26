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

class OfflineFirstMoonRepository(private val moonDAO: MoonDAO, private val moonApi: MoonApiService) :
    MoonRepository {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateMoonsInBackground()
        }
    }

    private suspend fun updateMoonsInBackground() {
        while (true) {
            refreshMoons()
            delay(3000)
        }

    }
    override fun getAllMoonsStream(): Flow<List<Moon>> {
        return moonDAO.getAllMoons().map { moons -> moons.map { it.asDomainMoon() } }
            .onEach {
                if (it.isEmpty()) {
                    refreshMoons()
                }
            }
    }

    override fun getMoonStream(id: Int): Flow<Moon?> {
        return moonDAO.getMoon(id).map { moon -> moon?.asDomainMoon() }
    }

    override fun getAllMoonsByPlanetStream(id : Int): Flow<List<Moon?>> {
        return moonDAO.getMoonsByPlanet(id).map {moon -> moon.map{ it.asDomainMoon()}}
    }



    override suspend fun refreshMoons() {
        moonApi.getMoons()
            .also {
                    externalMoons -> moonDAO.deleteAndInsert(
                moons = externalMoons.map(Moon::asMoonDatabase)
            )

            }
    }



}
