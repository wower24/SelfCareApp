package com.wower.selfcareapp.util

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Book
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Journal: BottomNavItem(
        route = Screen.JournalScreen.route,
        label = "Journal",
        icon = Icons.Default.Book
    )

    object Breathing: BottomNavItem(
        route = Screen.BoxBreathingScreen.route,
        label = "Breathe",
        icon = Icons.Default.Air
    )
}