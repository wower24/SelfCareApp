package com.wower.selfcareapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wower.selfcareapp.feature_journal.presentation.journal.JournalScreen
import com.wower.selfcareapp.feature_journal.presentation.journal_entry.JournalEntryScreen
import com.wower.selfcareapp.feature_journal.presentation.util.Screen
import com.wower.selfcareapp.ui.theme.SelfCareAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfCareAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.primary
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.JournalScreen.route
                    ) {
                        composable(route = Screen.JournalScreen.route) {
                            JournalScreen(navController = navController)
                        }
                        composable(
                            route = Screen.JournalEntryScreen.route + "?noteId={noteId}",
                            arguments = listOf(
                                navArgument(name = "noteId") {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            JournalEntryScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }

}