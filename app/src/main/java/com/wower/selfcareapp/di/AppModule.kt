package com.wower.selfcareapp.di

import android.app.Application
import androidx.room.Room
import com.wower.selfcareapp.feature_journal.data.data_source.SelfCareDatabase
import com.wower.selfcareapp.feature_journal.data.repository.JournalEntryRepositoryImpl
import com.wower.selfcareapp.feature_journal.domain.repository.JournalEntryRepository
import com.wower.selfcareapp.feature_journal.domain.use_case.AddEntry
import com.wower.selfcareapp.feature_journal.domain.use_case.DeleteEntry
import com.wower.selfcareapp.feature_journal.domain.use_case.EntryUseCases
import com.wower.selfcareapp.feature_journal.domain.use_case.GetEntries
import com.wower.selfcareapp.feature_journal.domain.use_case.GetEntryById
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): SelfCareDatabase {
        return Room.databaseBuilder(
            app,
            SelfCareDatabase::class.java,
            SelfCareDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideJournalEntryRepository(db: SelfCareDatabase): JournalEntryRepository {
        return JournalEntryRepositoryImpl(db.journalEntryDao)
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
}

