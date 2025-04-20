package com.wower.selfcareapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wower.selfcareapp.feature_affirmation.presentation.affirmation.AffirmationScreen
import com.wower.selfcareapp.feature_breathing.presentation.box_breathing.BoxBreathingScreen
import com.wower.selfcareapp.feature_journal.presentation.journal.JournalScreen
import com.wower.selfcareapp.feature_journal.presentation.journal_entry.JournalEntryScreen
import com.wower.selfcareapp.util.Screen
import com.wower.selfcareapp.ui.theme.SelfCareAppTheme
import com.wower.selfcareapp.util.BottomNavItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfCareAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                            NavHost(
                                navController = navController,
                                startDestination = Screen.AffirmationScreen.route
                            ) {
                                composable(route = Screen.AffirmationScreen.route) {
                                    AffirmationScreen(navController = navController)
                                }
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
                                composable(route = Screen.BoxBreathingScreen.route) {
                                    BoxBreathingScreen(navController)
                                }
                    }

                }
            }

        }
    }

}