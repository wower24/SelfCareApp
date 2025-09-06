package com.wower.selfcareapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.wower.selfcareapp.feature_affirmation.data.repository.AffirmationRepositoryImpl
import com.wower.selfcareapp.feature_affirmation.domain.repository.AffirmationRepository
import com.wower.selfcareapp.feature_affirmation.domain.use_case.GetRandomAffirmation
import com.wower.selfcareapp.feature_grounding.domain.use_case.GetRandomGroundingExercise
import com.wower.selfcareapp.feature_journal.data.repository.JournalEntryRepositoryImpl
import com.wower.selfcareapp.feature_journal.data.repository.JournalPromptRepositoryImpl
import com.wower.selfcareapp.feature_journal.domain.repository.JournalEntryRepository
import com.wower.selfcareapp.feature_journal.domain.repository.JournalPromptRepository
import com.wower.selfcareapp.feature_journal.domain.use_case.AddEntry
import com.wower.selfcareapp.feature_journal.domain.use_case.DeleteEntry
import com.wower.selfcareapp.feature_journal.domain.use_case.EntryUseCases
import com.wower.selfcareapp.feature_journal.domain.use_case.GetEntries
import com.wower.selfcareapp.feature_journal.domain.use_case.GetEntryById
import com.wower.selfcareapp.feature_journal.domain.use_case.GetRandomPrompt
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
        return Room.databaseBuilder(
            app,
            SelfCareDatabase::class.java,
            SelfCareDatabase.DATABASE_NAME
        ).createFromAsset("self_care_db.db")
            .fallbackToDestructiveMigrationFrom(1)
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
    fun provideAffirmationRepository(db: SelfCareDatabase) : AffirmationRepository {
        return AffirmationRepositoryImpl(db.affirmationDao)
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

    @Provides
    @Singleton // If you want a single instance for the app's lifetime
    fun provideGetRandomPrompt(repository: JournalPromptRepository): GetRandomPrompt {
        return GetRandomPrompt(repository)
    }

    @Provides
    @Singleton
    fun provideGetRandomAffirmation(repository: AffirmationRepository): GetRandomAffirmation {
        return GetRandomAffirmation(repository)
    }

    @Provides
    @Singleton
    fun provideGetRandomGroundingExercise(): GetRandomGroundingExercise {
        return GetRandomGroundingExercise()
    }
}

