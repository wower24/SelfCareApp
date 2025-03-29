package com.wower.selfcareapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wower.selfcareapp.data.local.model.JournalPrompt
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalPromptDao {
    //insert prompts only at the beginning - no prompts should be added or deleted
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journalPrompt: JournalPrompt)
    //get a random prompt that is not used yet
    @Query("SELECT * FROM journal_prompts WHERE isNotUsed = 1 LIMIT 1")
    fun getRandomPrompt(): Flow<JournalPrompt>
    //mark prompt as used so it's not repeated in the cycle
    @Query("UPDATE journal_prompts SET isNotUsed = 0 WHERE id = :promptId")
    suspend fun markPromptAsUsed(promptId: Int)
    //after all prompts are used, reset them to repeat the cycle
    @Query("UPDATE journal_prompts SET isNotUsed = 1")
    suspend fun resetPrompts()
}