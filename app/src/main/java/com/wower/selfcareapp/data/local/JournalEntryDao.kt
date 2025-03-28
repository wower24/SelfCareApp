package com.wower.selfcareapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wower.selfcareapp.data.local.model.JournalEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journalEntry: JournalEntry)

    @Query("DELETE FROM journal_entries WHERE id = :entryId")
    suspend fun delete(entryId: Int)

    @Query("SELECT * FROM journal_entries ORDER BY date DESC")
    suspend fun getAllEntries(): Flow<List<JournalEntry>>

    @Query("SELECT * FROM journal_entries WHERE id = :entryId")
    suspend fun getEntryById(entryId: Int): Flow<JournalEntry?>

    @Query("SELECT * FROM journal_entries WHERE prompt = :prompt ORDER BY date DESC")
    suspend fun getEntriesByPrompt(prompt: String): Flow<List<JournalEntry>>
}
