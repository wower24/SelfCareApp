package com.wower.selfcareapp.data.repository
import com.wower.selfcareapp.domain.repositories.AffirmationRepository
import com.wower.selfcareapp.domain.repositories.JournalEntryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class JournalEntryRepositoryModule {
    //bind repository so it's not provided directly
    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: JournalEntryRepositoryImpl): JournalEntryRepository
}