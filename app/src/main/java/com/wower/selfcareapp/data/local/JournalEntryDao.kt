package com.wower.selfcareapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wower.selfcareapp.data.local.model.JournalEntry
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.Date

@Dao
interface JournalEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journalEntry: JournalEntry)

    @Query("DELETE FROM journal_entries WHERE id = :entryId")
    suspend fun delete(entryId: Int)

    @Query("SELECT * FROM journal_entries ORDER BY date DESC")
    fun getAllEntries(): Flow<List<JournalEntry>>

    @Query("SELECT * FROM journal_entries WHERE id = :entryId")
    fun getEntryById(entryId: Int): Flow<JournalEntry?>

    @Query("SELECT * FROM journal_entries WHERE prompt = :prompt ORDER BY date DESC")
    fun getEntriesByPrompt(prompt: String): Flow<List<JournalEntry>>

    @Query("SELECT * FROM journal_entries WHERE date = :date LIMIT 1")
    fun getEntryByDate(date: LocalDate): Flow<JournalEntry?>
}
