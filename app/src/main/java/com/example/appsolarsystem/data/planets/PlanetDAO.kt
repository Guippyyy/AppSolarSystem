package com.example.appsolarsystem.data.planets


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDAO {

    @Query("SELECT * from planets")
    fun getAllPlanets(): Flow<List<PlanetDatabase>>


    @Query("SELECT * from planets WHERE planetID = :id")
    fun getPlanet(id: Int): Flow<PlanetDatabase?>

    @Query("DELETE from planets")
    suspend fun deleteAllPlanets()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanets(planets : List<PlanetDatabase>) : List<Long>


    @Transaction
    suspend fun deleteAndInsert(planets : List<PlanetDatabase>){
        deleteAllPlanets()
        insertPlanets(planets)
    }
}