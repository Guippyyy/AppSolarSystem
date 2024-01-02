package com.example.appsolarsystem.data.quickFacts

import com.example.appsolarsystem.model.QuickFact
import com.example.appsolarsystem.network.QuickFactApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Repository responsible for managing quick facts data. This repository follows an offline-first approach,
 * syncing data in the background at regular intervals. It provides methods for observing streams of quick facts data,
 * fetching quick facts related to a specific planet, and refreshing the quick facts data.
 *
 * @param quickFactDAO The Data Access Object (DAO) for local quick facts database interactions.
 * @param quickFactApi The API service for fetching quick facts data from a remote source.
 */
class OfflineFirstQuickFactRepository(
    private val quickFactDAO: QuickFactDAO,
    private val quickFactApi: QuickFactApiService
) : QuickFactRepository {

    /**
     * Initializes the repository by launching a coroutine to update quick facts data in the background at regular intervals.
     */
    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateQuickFactInBackground()
        }
    }

    /**
     * Periodically updates quick facts data in the background at regular intervals.
     */
    private suspend fun updateQuickFactInBackground() {
        while (true) {
            refreshQuickFact()
            delay(3000)
        }
    }

    /**
     * Observes a stream of all quick facts.
     *
     * @return A [Flow] emitting a list of [QuickFact] objects representing all quick facts.
     */
    override fun getAllQuickFactStream(): Flow<List<QuickFact>> {
        return quickFactDAO.getAllQuickFact().map { quickFacts -> quickFacts.map { it.asDomainQuickFact() } }
            .onEach {
                if (it.isEmpty()) {
                    refreshQuickFact()
                }
            }
    }

    /**
     * Observes a stream of quick facts related to a specific planet identified by its ID.
     *
     * @param id The unique identifier of the planet.
     * @return A [Flow] emitting a list of [QuickFact] objects representing quick facts related to the specified planet.
     */
    override fun getAllQuickFactByPlanetStream(id: Int): Flow<List<QuickFact>> {
        return quickFactDAO.getQuickFactByPlanet(id).map { quickFacts -> quickFacts.map { it.asDomainQuickFact() } }
    }

    /**
     * Refreshes the quick facts data by fetching the latest data from the remote source.
     * This operation is performed asynchronously.
     */
    override suspend fun refreshQuickFact() {
        quickFactApi.getQuickFact()
            .also { externalQuickFact ->
                quickFactDAO.deleteAndInsert(
                    quickFacts = externalQuickFact.map(QuickFact::asQuickFactDatabase)
                )
            }
    }
}
