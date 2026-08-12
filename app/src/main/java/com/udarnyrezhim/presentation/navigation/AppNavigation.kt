package com.udarnyrezhim.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.udarnyrezhim.presentation.calendar.CalendarScreen
import com.udarnyrezhim.presentation.focus.FocusActiveScreen
import com.udarnyrezhim.presentation.focus.FocusCompletionScreen
import com.udarnyrezhim.presentation.focus.FocusSetupScreen
import com.udarnyrezhim.presentation.home.HomeScreen
import com.udarnyrezhim.presentation.settings.SettingsScreen
import com.udarnyrezhim.presentation.statistics.StatisticsScreen
import com.udarnyrezhim.presentation.streak.StreakScreen
import com.udarnyrezhim.presentation.tasks.TasksScreen

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object FocusSetup : Screen("focus_setup")
    data object FocusActive : Screen("focus_active")
    data object FocusComplete : Screen("focus_complete")
    data object Tasks : Screen("tasks")
    data object Statistics : Screen("statistics")
    data object Calendar : Screen("calendar")
    data object Streak : Screen("streak")
    data object Settings : Screen("settings")
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                onStartFocus = { navController.navigate(Screen.FocusSetup.route) }
            )
        }
        composable(Screen.FocusSetup.route) {
            FocusSetupScreen(
                onStartFocus = { _, _ ->
                    navController.navigate(Screen.FocusActive.route)
                }
            )
        }
        composable(Screen.FocusActive.route) {
            FocusActiveScreen(
                onComplete = { navController.navigate(Screen.FocusComplete.route) }
            )
        }
        composable(Screen.FocusComplete.route) {
            FocusCompletionScreen(
                onContinue = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Tasks.route) { TasksScreen() }
        composable(Screen.Statistics.route) { StatisticsScreen() }
        composable(Screen.Calendar.route) { CalendarScreen() }
        composable(Screen.Streak.route) { StreakScreen() }
        composable(Screen.Settings.route) { SettingsScreen() }
    }
}
