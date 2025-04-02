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
    fun `test new entry is created when no entry exists for today`() = runTest {
        // Given
        coEvery { getEntryByDateUseCase(LocalDate.now()) } returns flowOf(null)
        coEvery { getRandomJournalPromptUseCase() } returns flowOf(JournalPrompt(id = 1, text = "Test Prompt"))
        coEvery { addJournalEntryUseCase(any()) }

        // When
        viewModel = JournalEntryViewModel(
            addJournalEntryUseCase,
            getEntryByIdUseCase,
            getRandomJournalPromptUseCase,
            getEntryByDateUseCase,
            -1
        )
        viewModel.onContentChange("Test Content")
        viewModel.addEntry()
        // Then
        val expectedEntry = JournalEntry(
            id = 1,
            prompt = "Test Prompt",
            content = "Test Content",
            date = LocalDate.now()
        )
        assertTrue(viewModel.state.isEntryAdded)
        assertTrue(viewModel.state.hasEntryForToday)
        assertEquals(expectedEntry.content, viewModel.state.content)
        assertEquals(expectedEntry.prompt, viewModel.state.prompt)
        assertEquals(expectedEntry.date, viewModel.state.date)
    }

    @Test
    fun LoadNewEntryTest() = runTest {
        // Given
        coEvery { getEntryByDateUseCase(LocalDate.now()) } returns flowOf(null)
        coEvery { getRandomJournalPromptUseCase() } returns flowOf(JournalPrompt(id = 1, text = "Random Prompt"))
        coEvery { addJournalEntryUseCase(any()) }

        // When
        viewModel = JournalEntryViewModel(
            addJournalEntryUseCase,
            getEntryByIdUseCase,
            getRandomJournalPromptUseCase,
            getEntryByDateUseCase,
            -1
        )

        // Then
        assertEquals("Random Prompt", viewModel.state.prompt)
    }

    @Test
    fun LoadExistingEntryTest() = runTest {
        // Given
        val existingEntry = JournalEntry(
            id = 123,
            prompt = "Loaded Prompt",
            content = "Loaded Content",
            date = LocalDate.now()
        )
        // Assuming you have a getJournalEntryById use case or similar
        // and a corresponding getEntryById method in the ViewModel
        // If not, you'll need to adjust this part.
        // Replace this with your actual logic for loading by ID:
        // coEvery { getEntryByIdUseCase(123) } returns existingEntry
        coEvery { getEntryByDateUseCase(any()) } returns flowOf(existingEntry)
        // When
        viewModel = JournalEntryViewModel(
            addJournalEntryUseCase,
            getEntryByIdUseCase,
            getRandomJournalPromptUseCase,
            getEntryByDateUseCase,
            123 // Pass a valid entryId
        )

        // Then
        // Adapt these assertions to your actual loading logic:
        assertEquals(123, viewModel.state.id)
        assertEquals("Loaded Prompt", viewModel.state.prompt)
        assertEquals("Loaded Content", viewModel.state.content)
        assertEquals(existingEntry.date, viewModel.state.date)
        assertTrue(viewModel.state.isUpdatingEntry)
    }

}