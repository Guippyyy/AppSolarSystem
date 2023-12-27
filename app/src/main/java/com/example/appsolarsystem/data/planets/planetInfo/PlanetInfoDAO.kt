package com.example.appsolarsystem.data.planets.planetInfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.appsolarsystem.data.moons.MoonDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetInfoDAO {

    @Query("SELECT * from planetInfo")
    fun getAllplanetInfo(): Flow<List<PlanetInfoDatabase>>


    @Query("SELECT * from planetInfo WHERE id = :id")
    fun getPlanetInfo(id: Int): Flow<PlanetInfoDatabase?>

    @Query("SELECT * from planetInfo WHERE planetID = :id")
    fun getPlanetInfoByPlanet(id : Int) : Flow<List<PlanetInfoDatabase>>

    @Query("DELETE from planetInfo")
    suspend fun deleteAllPlanetInfos()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanetInfos(planetInfos : List<PlanetInfoDatabase>) : List<Long>


    @Transaction
    suspend fun deleteAndInsert(planetInfos : List<PlanetInfoDatabase>){
        deleteAllPlanetInfos()
        insertPlanetInfos(planetInfos)
    }



}
