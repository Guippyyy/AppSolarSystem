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

@Database(entities = [PlanetDatabase::class, MoonDatabase::class, PlanetInfoDatabase::class, QuickFactDatabase::class], version = 6, exportSchema = false)
abstract class SolarSystemDatabase : RoomDatabase() {

    abstract fun planetDAO(): PlanetDAO

    abstract fun moonDAO(): MoonDAO

    abstract fun planetInfoDAO() : PlanetInfoDAO

    abstract fun quickFactDAO() : QuickFactDAO

    companion object {
        @Volatile
        private var Instance: SolarSystemDatabase? = null

        fun getDatabase(context: Context): SolarSystemDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, SolarSystemDatabase::class.java, "solarSystem_database").fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}