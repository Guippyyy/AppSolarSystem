package com.example.appsolarsystem.data.planets


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
/**
 * Data Access Object (DAO) for planets. Provides methods for accessing and manipulating planet data in the local database.
 */
@Dao
interface PlanetDAO {

    /**
     * Get a flow of all planets.
     *
     * @return A [Flow] emitting a list of [PlanetDatabase] objects representing all available planets.
     */
    @Query("SELECT * from planets")
    fun getAllPlanets(): Flow<List<PlanetDatabase>>

    /**
     * Get a flow of a specific planet.
     *
     * @param id The identifier of the planet for which data is requested.
     * @return A [Flow] emitting a single [PlanetDatabase] object representing the specified planet, or null if not found.
     */
    @Query("SELECT * from planets WHERE planetID = :id")
    fun getPlanet(id: Int): Flow<PlanetDatabase?>

    /**
     * Delete all planets from the local database.
     */
    @Query("DELETE from planets")
    suspend fun deleteAllPlanets()

    /**
     * Insert a list of planets into the local database.
     *
     * @param planets The list of [PlanetDatabase] objects to be inserted.
     * @return A list of Long values representing the inserted row IDs.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanets(planets: List<PlanetDatabase>): List<Long>

    /**
     * Delete all planets and insert a new list of planets into the local database in a single transaction.
     *
     * @param planets The list of [PlanetDatabase] objects to be inserted.
     */
    @Transaction
    public suspend fun deleteAndInsert(planets: List<PlanetDatabase>){
        deleteAllPlanets()
        insertPlanets(planets)
    }
}
