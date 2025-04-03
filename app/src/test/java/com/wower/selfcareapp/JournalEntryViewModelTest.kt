package com.wower.selfcareapp

import com.wower.selfcareapp.data.local.model.JournalEntry
import com.wower.selfcareapp.data.local.model.JournalPrompt
import com.wower.selfcareapp.domain.use_cases.journal_entry.AddJournalEntryUseCase
import com.wower.selfcareapp.domain.use_cases.journal_entry.GetEntryByDateUseCase
import com.wower.selfcareapp.domain.use_cases.journal_entry.GetEntryByIdUseCase
import com.wower.selfcareapp.domain.use_cases.journal_prompt.GetRandomPromptUseCase
import com.wower.selfcareapp.presentation.journal_entry.JournalEntryViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.Int

class JournalEntryViewModelTest {
    private val addJournalEntryUseCase: AddJournalEntryUseCase = mockk()
    private val getEntryByDateUseCase: GetEntryByDateUseCase = mockk()
    private val getRandomJournalPromptUseCase: GetRandomPromptUseCase = mockk()
    private val getEntryByIdUseCase: GetEntryByIdUseCase = mockk()

    private lateinit var viewModel: JournalEntryViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadNewEntry() {

    }

}