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


class OfflineFirstQuickFactRepository(private val quickFactDAO: QuickFactDAO, private val quickFactApi : QuickFactApiService) : QuickFactRepository {


    init {
        CoroutineScope(Dispatchers.IO).launch {
            updateQuickFactInBackground()
        }
    }

    private suspend fun updateQuickFactInBackground() {
        while (true) {
            refreshQuickFact()
            delay(3000)
        }

    }

    override fun getAllQuickFactStream(): Flow<List<QuickFact>> {
        return quickFactDAO.getAllQuickFact().map { p -> p.map {it.asDomainQuickFact()}}
            .onEach {
                if(it.isEmpty()){
                    refreshQuickFact()
                }
            }
    }

    override fun getAllQuickFactByPlanetStream(id: Int): Flow<List<QuickFact>> {
        return quickFactDAO.getQuickFactByPlanet(id).map { p -> p.map { it.asDomainQuickFact()}}
    }

    override suspend fun refreshQuickFact() {
        quickFactApi.getQuickFact()
            .also {
                externalQuickFact -> quickFactDAO.deleteAndInsert(
                    quickFacts = externalQuickFact.map(QuickFact::asQuickFactDatabase)
                )
            }
    }
}
