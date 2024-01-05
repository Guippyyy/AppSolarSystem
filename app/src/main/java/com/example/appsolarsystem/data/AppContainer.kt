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
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 * Interface defining the dependencies for the Solar System application.
 */
interface AppContainer {
    /**
     * Repository for handling planet-related data.
     */
    val planetRepository: PlanetRepository

    /**
     * Repository for handling moon-related data.
     */
    val moonRepository: MoonRepository

    /**
     * Repository for handling planet information-related data.
     */
    val planetInfoRepository: PlanetInfoRepository

    /**
     * Repository for handling quick facts-related data.
     */
    val quickFactRepository: QuickFactRepository
}
/**
 * Default implementation of [AppContainer] providing concrete instances of repositories.
 *
 * @property context The application context.
 */
class DefaultAppContainer(private val context: Context) : AppContainer {
    private val baseUrl = "http://10.0.2.2:3000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    /**
     * Lazily initialized repository for handling planet-related data.
     */
    override val planetRepository: PlanetRepository by lazy {
        OfflineFirstPlanetRepository(
            SolarSystemDatabase.getDatabase(context).planetDAO(),
            retrofit.create(PlanetApiService::class.java)
        )
    }

    /**
     * Lazily initialized repository for handling moon-related data.
     */
    override val moonRepository: MoonRepository by lazy {
        OfflineFirstMoonRepository(
            SolarSystemDatabase.getDatabase(context).moonDAO(),
            retrofit.create(MoonApiService::class.java)
        )
    }

    /**
     * Lazily initialized repository for handling planet information-related data.
     */
    override val planetInfoRepository: PlanetInfoRepository by lazy {
        OfflineFirstPlanetInfoRepository(
            SolarSystemDatabase.getDatabase(context).planetInfoDAO(),
            retrofit.create(PlanetInfoApiService::class.java)
        )
    }

    /**
     * Lazily initialized repository for handling quick facts-related data.
     */
    override val quickFactRepository: QuickFactRepository by lazy {
        OfflineFirstQuickFactRepository(
            SolarSystemDatabase.getDatabase(context).quickFactDAO(),
            retrofit.create(QuickFactApiService::class.java)
        )
    }
}
