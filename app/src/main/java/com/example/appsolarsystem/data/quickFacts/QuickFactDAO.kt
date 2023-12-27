package com.example.appsolarsystem.data.quickFacts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface QuickFactDAO {

    @Query("SELECT * from quickFacts")
    fun getAllQuickFact() : Flow<List<QuickFactDatabase>>

    @Query("SELECT * from quickFacts WHERE planetID = :id")
    fun getQuickFactByPlanet(id : Int) : Flow<List<QuickFactDatabase>>

    @Query("DELETE from quickFacts")
    suspend fun deleteAllQuickFact()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertQuickFact(quickFacts: List<QuickFactDatabase?>) : List<Long>

    @Transaction
    suspend fun deleteAndInsert(quickFacts: List<QuickFactDatabase?>){
        deleteAllQuickFact()
        insertQuickFact(quickFacts)
    }

}