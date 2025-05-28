package com.wower.selfcareapp

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import com.wower.selfcareapp.util.BottomNavItem
import com.wower.selfcareapp.util.Screen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavItem>
) {
    val selectedIndex = rememberSaveable {
        mutableStateOf(1)
    }

    NavigationBar {
        items.forEachIndexed { index, destination ->
            NavigationBarItem(
                label = {
                    Text(text = destination.label)
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = destination.label
                    )
                },
                selected = index == selectedIndex.value,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(items[index].route) {
                        popUpTo(Screen.JournalScreen.route)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}