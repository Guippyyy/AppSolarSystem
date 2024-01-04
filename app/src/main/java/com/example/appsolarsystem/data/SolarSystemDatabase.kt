package com.example.appsolarsystem.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appsolarsystem.data.moons.MoonDatabase
import com.example.appsolarsystem.data.planets.PlanetDAO
import com.example.appsolarsystem.data.planets.PlanetDatabase
import com.example.appsolarsystem.data.moons.MoonDAO
import com.example.appsolarsystem.data.planets.planetInfo.PlanetInfoDAO
import com.example.appsolarsystem.data.planets.planetInfo.PlanetInfoDatabase
import com.example.appsolarsystem.data.quickFacts.QuickFactDAO
import com.example.appsolarsystem.data.quickFacts.QuickFactDatabase
/**
 * Room Database class for managing entities related to the Solar System application.
 *
 * This database includes tables for [Planet], [Moon], [PlanetInfo], and [QuickFact] entities.
 *
 * @property planetDAO Data Access Object for interacting with the [Planet] table.
 * @property moonDAO Data Access Object for interacting with the [Moon] table.
 * @property planetInfoDAO Data Access Object for interacting with the [PlanetInfo] table.
 * @property quickFactDAO Data Access Object for interacting with the [QuickFact] table.
 */
@Database(entities = [PlanetDatabase::class, MoonDatabase::class, PlanetInfoDatabase::class, QuickFactDatabase::class], version = 7 , exportSchema = false)
abstract class SolarSystemDatabase : RoomDatabase() {

    abstract fun planetDAO(): PlanetDAO

    abstract fun moonDAO(): MoonDAO

    abstract fun planetInfoDAO() : PlanetInfoDAO

    abstract fun quickFactDAO() : QuickFactDAO

    companion object {
        @Volatile
        private var Instance: SolarSystemDatabase? = null

        /**
         * Get an instance of the [SolarSystemDatabase].
         *
         * @param context The application context.
         * @return The [SolarSystemDatabase] instance.
         */
        fun getDatabase(context: Context): SolarSystemDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, SolarSystemDatabase::class.java, "solarSystem_database").fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
