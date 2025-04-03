package com.wower.selfcareapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.wower.selfcareapp.presentation.journal.JournalScreen
import com.wower.selfcareapp.presentation.journal.JournalViewModel
import com.wower.selfcareapp.presentation.journal_entry.JournalEntryViewModelAssistedFactory
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wower.selfcareapp.presentation.journal_entry.JournalEntryScreen

//routes based on enum class
enum class Screens {
    Journal,
    JournalEntry
}

@Composable
fun NoteNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    journalViewModel: JournalViewModel,
    assistedFactory: JournalEntryViewModelAssistedFactory
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.Journal.name
    ) {
        composable(route = Screens.Journal.name) {
            val state by journalViewModel.state.collectAsState()
            JournalScreen(
                state = state,
                onDelete = journalViewModel::deleteEntry,
                onEntryClick = {
                    //add an optional parameter '?id'
                    navHostController.navigateToSingleTop("${Screens.JournalEntry.name}?id=$it")
                }
            )
        }

        //for this screen argument (id) is needed so the route is different
        //the {id} will be replaced by $it passed from other screens
        composable(
            route = "${Screens.JournalEntry.name}?id={id}",
            arguments = listOf(navArgument("id") {
                NavType.IntType
                defaultValue = -1
            })
        ) { backStackEntry ->
            //get id from the arguments
            //val name = it.arguments?.getType("argumentName")
            val id = backStackEntry.arguments?.getInt("id") ?: -1

            JournalEntryScreen(
                entryId = id,
                assistedFactory = assistedFactory,
                navigateUp = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}

//manage the screen switch
fun NavHostController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}