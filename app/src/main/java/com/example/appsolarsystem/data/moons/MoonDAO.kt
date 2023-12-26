package com.example.appsolarsystem.data.moons


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.appsolarsystem.data.planets.PlanetDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface MoonDAO {

    @Query("SELECT * from moons")
    fun getAllMoons(): Flow<List<MoonDatabase>>


    @Query("SELECT * from moons WHERE moonID = :id")
    fun getMoon(id: Int): Flow<MoonDatabase?>

    @Query("SELECT * from moons WHERE PlanetID = :id")
    fun getMoonsByPlanet(id : Int) : Flow<List<MoonDatabase>>

    @Query("DELETE from moons")
    suspend fun deleteAllMoons()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoons(moons : List<MoonDatabase>) : List<Long>


    @Transaction
    suspend fun deleteAndInsert(moons : List<MoonDatabase>){
        deleteAllMoons()
        insertMoons(moons)
    }



}