package com.example.appsolarsystem.data.planets


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDAO {

    @Query("SELECT * from planets")
    fun getAllPlanets(): Flow<List<PlanetDatabase>>


    @Query("SELECT * from planets WHERE planetID = :id")
    fun getPlanet(id: Int): Flow<PlanetDatabase?>

}