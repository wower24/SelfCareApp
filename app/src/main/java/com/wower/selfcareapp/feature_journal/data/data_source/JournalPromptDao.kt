package com.wower.selfcareapp.feature_journal.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt

@Dao
interface JournalPromptDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrompt(prompt: JournalPrompt)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPrompts(prompts: List<JournalPrompt>)

    @Query("SELECT * FROM journal_prompts WHERE isNotUsed = 1 ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomPrompt(): JournalPrompt?

    @Query("UPDATE journal_prompts SET isNotUsed = 0 WHERE id = :promptId")
    suspend fun markPromptAsUsed(promptId: Int)

    @Query("UPDATE journal_prompts SET isNotUsed = 1")
    suspend fun resetPrompts()
}