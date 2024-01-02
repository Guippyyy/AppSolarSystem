package com.example.appsolarsystem.data.planets.planetInfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.appsolarsystem.data.moons.MoonDatabase
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for accessing and managing [PlanetInfoDatabase] entities in the local database.
 */
@Dao
interface PlanetInfoDAO {

    /**
     * Get a [Flow] emitting a list of all planet info entities.
     *
     * @return A [Flow] emitting a list of [PlanetInfoDatabase] objects.
     */
    @Query("SELECT * from planetInfo")
    fun getAllplanetInfo(): Flow<List<PlanetInfoDatabase>>

    /**
     * Get a [Flow] emitting details of a specific planet info entity.
     *
     * @param id The identifier of the planet info.
     * @return A [Flow] emitting a nullable [PlanetInfoDatabase] object.
     */
    @Query("SELECT * from planetInfo WHERE id = :id")
    fun getPlanetInfo(id: Int): Flow<PlanetInfoDatabase?>

    /**
     * Get a [Flow] emitting a list of planet info entities associated with a specific planet.
     *
     * @param id The identifier of the planet.
     * @return A [Flow] emitting a list of [PlanetInfoDatabase] objects.
     */
    @Query("SELECT * from planetInfo WHERE planetID = :id")
    fun getPlanetInfoByPlanet(id: Int): Flow<List<PlanetInfoDatabase>>

    /**
     * Delete all planet info entities from the database.
     */
    @Query("DELETE from planetInfo")
    suspend fun deleteAllPlanetInfos()

    /**
     * Insert a list of planet info entities into the database, ignoring conflicts.
     *
     * @param planetInfos The list of [PlanetInfoDatabase] objects to be inserted.
     * @return A list of long values representing the row indices of the inserted entities.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPlanetInfos(planetInfos: List<PlanetInfoDatabase>): List<Long>

    /**
     * Delete all existing planet info entities and insert a new list of planet info entities into the database in a single transaction.
     *
     * @param planetInfos The list of [PlanetInfoDatabase] objects to be inserted.
     */
    @Transaction
    suspend fun deleteAndInsert(planetInfos: List<PlanetInfoDatabase>) {
        deleteAllPlanetInfos()
        insertPlanetInfos(planetInfos)
    }
}
