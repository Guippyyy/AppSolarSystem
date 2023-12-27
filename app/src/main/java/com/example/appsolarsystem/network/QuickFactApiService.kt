package com.example.appsolarsystem.network

import com.example.appsolarsystem.model.QuickFact
import retrofit2.http.GET
import retrofit2.http.Path

interface QuickFactApiService {

    @GET("quickFacts")
    suspend fun getQuickFact() : List<QuickFact>

    @GET("quickFacts/planetID/{planetID}")
    suspend fun getQuickFactByPlanetId(@Path("planetID") planetID : Int) : List<QuickFact>

}