package com.example.appsolarsystem.data.quickFacts

import com.example.appsolarsystem.model.QuickFact
import kotlinx.coroutines.flow.Flow

interface QuickFactRepository {

    fun getAllQuickFactStream() : Flow<List<QuickFact>>

    fun getAllQuickFactByPlanetStream(id : Int) : Flow<List<QuickFact>>
    suspend fun refreshQuickFact()

}