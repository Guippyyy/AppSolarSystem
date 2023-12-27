package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.PlanetInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetInfoApiService {

    @GET("planetInfo")
    suspend fun getPlanetInfo() : List<PlanetInfo>

    @GET("planetInfo/planetID/{planetID}")
    suspend fun getPlanetInfoByPlanetId(@Path("planetID") planetID : Int) : List<PlanetInfo>

}