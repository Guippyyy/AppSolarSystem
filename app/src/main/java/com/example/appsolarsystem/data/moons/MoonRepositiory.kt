package com.example.appsolarsystem.data.moons

import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.Planet
import kotlinx.coroutines.flow.Flow

interface MoonRepository {

    fun getAllMoonsStream() : Flow<List<Moon>>

    fun getAllMoonsByPlanetStream(id : Int) : Flow<List<Moon?>>

    fun getMoonStream(id : Int ) : Flow<Moon?>

    suspend fun refreshMoons()

}