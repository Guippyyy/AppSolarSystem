package com.example.appsolarsystem.data.moons


import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MoonDAO {

    @Query("SELECT * from moons")
    fun getAllMoons(): Flow<List<MoonDatabase>>


    @Query("SELECT * from moons WHERE moonID = :id")
    fun getMoon(id: Int): Flow<MoonDatabase?>

}