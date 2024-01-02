package com.example.appsolarsystem.data.quickFacts

import com.example.appsolarsystem.model.QuickFact
import kotlinx.coroutines.flow.Flow
/**
 * Repository interface for quick facts, providing methods to observe streams of quick facts and refresh the data.
 */
interface QuickFactRepository {

    /**
     * Observes a stream of all quick facts.
     *
     * @return A [Flow] emitting a list of [QuickFact] objects representing all quick facts.
     */
    fun getAllQuickFactStream(): Flow<List<QuickFact>>

    /**
     * Observes a stream of quick facts related to a specific planet identified by its ID.
     *
     * @param id The unique identifier of the planet.
     * @return A [Flow] emitting a list of [QuickFact] objects representing quick facts related to the specified planet.
     */
    fun getAllQuickFactByPlanetStream(id: Int): Flow<List<QuickFact>>

    /**
     * Refreshes the quick facts data by fetching the latest information from the remote data source.
     */
    suspend fun refreshQuickFact()
}
