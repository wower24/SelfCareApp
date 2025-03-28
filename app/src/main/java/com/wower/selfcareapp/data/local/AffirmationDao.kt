package com.wower.selfcareapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wower.selfcareapp.data.local.model.Affirmation
import com.wower.selfcareapp.data.local.model.JournalPrompt
import kotlinx.coroutines.flow.Flow

@Dao
interface AffirmationDao {
    //insert affirmations only at the beginning - no affirmations should be added or deleted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(affirmation: Affirmation)
    //get a random affirmation that is not used yet
    @Query("SELECT * FROM affirmations WHERE isNotUsed = 1 LIMIT 1")
    suspend fun getRandomAffirmation(): Flow<Affirmation>
    //mark affirmation as used so it's not repeated in the cycle
    @Query("UPDATE affirmations SET isNotUsed = 0 WHERE id = :affirmationId")
    suspend fun markAffirmationAsUsed(affirmationId: Int)
    //after all affirmations are used, reset them to repeat the cycle
    @Query("UPDATE affirmations SET isNotUsed = 1")
    suspend fun resetAffirmations()
}