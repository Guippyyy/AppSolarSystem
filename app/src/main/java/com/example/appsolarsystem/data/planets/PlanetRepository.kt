package com.example.appsolarsystem.data.planets

import com.example.appsolarsystem.model.Planet
import kotlinx.coroutines.flow.Flow


interface PlanetRepository {

    fun getAllPlanetsStream() : Flow<List<Planet>>

    fun getPlanetStream(id : Int ) : Flow<Planet?>

    suspend fun refreshPlanets()

}

