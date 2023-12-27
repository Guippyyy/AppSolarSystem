package com.example.appsolarsystem.data

import android.content.Context
import com.example.appsolarsystem.data.moons.MoonRepository
import com.example.appsolarsystem.data.moons.OfflineFirstMoonRepository
import com.example.appsolarsystem.data.planets.OfflineFirstPlanetRepository
import com.example.appsolarsystem.data.planets.PlanetRepository
import com.example.appsolarsystem.data.planets.planetInfo.OfflineFirstPlanetInfoRepository
import com.example.appsolarsystem.data.planets.planetInfo.PlanetInfoRepository
import com.example.appsolarsystem.data.quickFacts.OfflineFirstQuickFactRepository
import com.example.appsolarsystem.data.quickFacts.QuickFactRepository
import com.example.appsolarsystem.network.MoonApiService
import com.example.appsolarsystem.network.PlanetApiService
import com.example.appsolarsystem.network.PlanetInfoApiService
import com.example.appsolarsystem.network.QuickFactApiService
import com.example.appsolarsystem.ui.views.QuickFactViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val planetRepository : PlanetRepository
    val moonRepository : MoonRepository
    val planetInfoRepository : PlanetInfoRepository
    val quickFactRepository : QuickFactRepository
}

class DefaultAppContainer(private val context : Context) : AppContainer{
    private val baseUrl = "http://192.168.100.101:3000/"


    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()


    override val planetRepository : PlanetRepository by lazy {
        OfflineFirstPlanetRepository(
            SolarSystemDatabase.getDatabase(context).planetDAO(),
            retrofit.create(PlanetApiService::class.java)
        )
    }
    override val moonRepository: MoonRepository by lazy {
        OfflineFirstMoonRepository(
            SolarSystemDatabase.getDatabase(context).moonDAO(),
            retrofit.create(MoonApiService::class.java)
        )
    }

    override val planetInfoRepository : PlanetInfoRepository by lazy {
        OfflineFirstPlanetInfoRepository(
            SolarSystemDatabase.getDatabase(context).planetInfoDAO(),
            retrofit.create(PlanetInfoApiService::class.java)
        )
    }

    override val quickFactRepository: QuickFactRepository by lazy {
        OfflineFirstQuickFactRepository(
            SolarSystemDatabase.getDatabase(context).quickFactDAO(),
            retrofit.create(QuickFactApiService::class.java)
        )
    }

}


