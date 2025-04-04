package com.wower.selfcareapp.data.repository

import com.wower.selfcareapp.domain.repositories.AffirmationRepository
import com.wower.selfcareapp.domain.repositories.JournalPromptRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class JournalPromptRepositoryModule {
    //bind repository so it's not provided directly
    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: JournalPromptRepositoryImpl): JournalPromptRepository
}