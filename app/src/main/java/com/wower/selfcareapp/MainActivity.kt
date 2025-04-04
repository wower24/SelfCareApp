package com.wower.selfcareapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.wower.selfcareapp.presentation.journal.JournalViewModel
import com.wower.selfcareapp.presentation.journal_entry.JournalEntryViewModelAssistedFactory
import com.wower.selfcareapp.presentation.navigation.AppNavigation
import com.wower.selfcareapp.presentation.navigation.navigateToSingleTop
import com.wower.selfcareapp.ui.theme.SelfCareAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var assistedFactory: JournalEntryViewModelAssistedFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfCareAppTheme {
                SelfCareApp()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SelfCareApp() {
        val journalViewModel: JournalViewModel = viewModel()
        val navController = rememberNavController()
        var currentTab by remember {
            mutableStateOf(Tabs.Journal)
        }

        Scaffold(
            bottomBar = {
                BottomAppBar(
                    actions = {
                        Row(horizontalArrangement = Arrangement.Center) {
                            InputChip(
                                selected = currentTab == Tabs.Journal,
                                onClick = {
                                    currentTab = Tabs.Journal
                                    navController.navigateToSingleTop(Tabs.Journal.name)
                                },
                                label = {
                                    Text(text = "Journal")
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.AccountBox,
                                        contentDescription = "Go to Journal"
                                    )
                                }
                            )

                            Spacer(modifier = Modifier.Companion.size(12.dp))

                            InputChip(
                                selected = currentTab == Tabs.JournalEntry,
                                onClick = {
                                    currentTab = Tabs.JournalEntry
                                    navController.navigateToSingleTop(Tabs.JournalEntry.name)
                                },
                                label = {
                                    Text(text = "Entry")
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.AddCircle,
                                        contentDescription = "Go to Journal Entry"
                                    )
                                }
                            )
                        }
                    }
                )
            }
        ) {
            AppNavigation(
                modifier = Modifier.padding(it),
                navHostController = navController,
                journalViewModel = journalViewModel,
                assistedFactory = assistedFactory
            )
        }
    }

    enum class Tabs {
        Journal,
        JournalEntry
    }
}

