package com.example.appsolarsystem.data.moons


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.appsolarsystem.data.planets.PlanetDatabase
import kotlinx.coroutines.flow.Flow
/**
 * Data Access Object (DAO) interface for Moon entities.
 *
 * This interface provides methods to interact with the underlying database for Moon-related operations.
 */
@Dao
interface MoonDAO {

    /**
     * Retrieves all moons from the database as a flow of lists.
     *
     * @return A flow emitting a list of [MoonDatabase] objects.
     */
    @Query("SELECT * from moons")
    fun getAllMoons(): Flow<List<MoonDatabase>>

    /**
     * Retrieves a single moon with the specified ID from the database as a flow.
     *
     * @param id The ID of the moon.
     * @return A flow emitting a single [MoonDatabase] object with the specified ID.
     */
    @Query("SELECT * from moons WHERE moonID = :id")
    fun getMoon(id: Int): Flow<MoonDatabase?>

    /**
     * Retrieves all moons associated with a specific planet ID from the database as a flow of lists.
     *
     * @param id The ID of the planet.
     * @return A flow emitting a list of [MoonDatabase] objects for the specified planet ID.
     */
    @Query("SELECT * from moons WHERE PlanetID = :id")
    fun getMoonsByPlanet(id: Int): Flow<List<MoonDatabase>>

    /**
     * Deletes all moons from the database.
     */
    @Query("DELETE from moons")
    suspend fun deleteAllMoons()

    /**
     * Inserts a list of moons into the database.
     *
     * @param moons The list of [MoonDatabase] objects to be inserted.
     * @return A list of IDs for the inserted moons.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMoons(moons: List<MoonDatabase>): List<Long>

    /**
     * Deletes all moons from the database and inserts a new list of moons in a single transaction.
     *
     * @param moons The list of [MoonDatabase] objects to be inserted.
     */
    @Transaction
    suspend fun deleteAndInsert(moons: List<MoonDatabase>) {
        deleteAllMoons()
        insertMoons(moons)
    }
}
