package com.wower.selfcareapp.di

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Callback
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wower.selfcareapp.feature_journal.data.data_source.SelfCareDatabase
import com.wower.selfcareapp.feature_journal.data.data_source.textFileDataToList
import com.wower.selfcareapp.feature_journal.data.repository.JournalEntryRepositoryImpl
import com.wower.selfcareapp.feature_journal.data.repository.JournalPromptRepositoryImpl
import com.wower.selfcareapp.feature_journal.domain.model.JournalPrompt
import com.wower.selfcareapp.feature_journal.domain.repository.JournalEntryRepository
import com.wower.selfcareapp.feature_journal.domain.repository.JournalPromptRepository
import com.wower.selfcareapp.feature_journal.domain.use_case.AddEntry
import com.wower.selfcareapp.feature_journal.domain.use_case.DeleteEntry
import com.wower.selfcareapp.feature_journal.domain.use_case.EntryUseCases
import com.wower.selfcareapp.feature_journal.domain.use_case.GetEntries
import com.wower.selfcareapp.feature_journal.domain.use_case.GetEntryById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(
        app: Application,
        @ApplicationContext context: Context
    ): SelfCareDatabase {
        textFileDataToList(context, "journal_prompts.txt")
        return Room.databaseBuilder(
            app,
            SelfCareDatabase::class.java,
            SelfCareDatabase.DATABASE_NAME
        ).createFromAsset("self_care_db.db")
            .fallbackToDestructiveMigrationFrom(2)
            .build()
    }

    @Provides
    @Singleton
    fun provideJournalEntryRepository(db: SelfCareDatabase): JournalEntryRepository {
        return JournalEntryRepositoryImpl(db.journalEntryDao)
    }

    @Provides
    @Singleton
    fun provideJournalPromptRepository(db: SelfCareDatabase) :JournalPromptRepository {
        return JournalPromptRepositoryImpl(db.journalPromptDao)
    }

    @Provides
    @Singleton
    fun provideEntryUseCases(repository: JournalEntryRepository): EntryUseCases {
        return EntryUseCases(
            getEntries = GetEntries(repository),
            deleteEntry = DeleteEntry(repository),
            addEntry = AddEntry(repository),
            getEntryById = GetEntryById(repository)
        )
    }

    private fun SelfCareDatabase.Companion.getPrepopulatedCallback(context: Context): Callback {
        return object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val prompts = textFileDataToList(context, "journal_prompts.txt")
                prompts.forEach { prompt ->
                    val journalPrompt = JournalPrompt(prompt = prompt)
                    db.execSQL(
                        "INERT OR IGNORE INTO journal_prompts (prompt, isNotUsed) VALUES ('${journalPrompt.prompt}', ${journalPrompt.isNotUsed})"
                    )
                }

            }
        }
    }
}

