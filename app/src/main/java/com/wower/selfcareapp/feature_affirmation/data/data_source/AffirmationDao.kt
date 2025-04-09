package com.wower.selfcareapp.feature_affirmation.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wower.selfcareapp.feature_affirmation.domain.model.Affirmation
import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt

@Dao
interface AffirmationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAffirmation(affirmation: Affirmation)

    @Query("SELECT * FROM affirmations WHERE isNotUsed = 1 ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomAffirmation(): Affirmation?

    @Query("UPDATE affirmations SET isNotUsed = 0 WHERE id = :affirmationId")
    suspend fun markAffirmationAsUsed(affirmationId: Int)

    @Query("UPDATE affirmations SET isNotUsed = 1")
    suspend fun resetAffirmations()
}