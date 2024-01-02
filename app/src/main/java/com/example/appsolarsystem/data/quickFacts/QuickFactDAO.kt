package com.example.appsolarsystem.data.quickFacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
/**
 * Data Access Object (DAO) interface for quick facts, providing methods for database operations related to quick facts.
 */
@Dao
interface QuickFactDAO {

    /**
     * Observes a stream of all quick facts.
     *
     * @return A [Flow] emitting a list of [QuickFactDatabase] objects representing all quick facts.
     */
    @Query("SELECT * from quickFacts")
    fun getAllQuickFact(): Flow<List<QuickFactDatabase>>

    /**
     * Observes a stream of quick facts related to a specific planet identified by its ID.
     *
     * @param id The unique identifier of the planet.
     * @return A [Flow] emitting a list of [QuickFactDatabase] objects representing quick facts related to the specified planet.
     */
    @Query("SELECT * from quickFacts WHERE planetID = :id")
    fun getQuickFactByPlanet(id: Int): Flow<List<QuickFactDatabase>>

    /**
     * Deletes all quick facts from the database.
     */
    @Query("DELETE from quickFacts")
    suspend fun deleteAllQuickFact()

    /**
     * Inserts a list of quick facts into the database, ignoring conflicts.
     *
     * @param quickFacts The list of [QuickFactDatabase] objects to be inserted.
     * @return A list of unique IDs for the inserted quick facts.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuickFact(quickFacts: List<QuickFactDatabase?>): List<Long>

    /**
     * Deletes all quick facts from the database and inserts a new list of quick facts.
     *
     * @param quickFacts The list of [QuickFactDatabase] objects to be inserted.
     */
    @Transaction
    suspend fun deleteAndInsert(quickFacts: List<QuickFactDatabase?>) {
        deleteAllQuickFact()
        insertQuickFact(quickFacts)
    }
}
