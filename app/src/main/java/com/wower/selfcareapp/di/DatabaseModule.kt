package com.wower.selfcareapp.di

import android.content.Context
import androidx.room.Room
import com.wower.selfcareapp.data.local.JournalEntryDao
import com.wower.selfcareapp.data.local.SelfCareDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideJournalEntryDao(database: SelfCareDatabase): JournalEntryDao = database.journalEntryDao

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SelfCareDatabase =
        Room.databaseBuilder(
            context,
            SelfCareDatabase::class.java,
            "self_care_database"
        )
            .build()
}