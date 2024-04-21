package com.example.simplechat.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplechat.ui.screens.chat.ChatScreen
import com.example.simplechat.ui.screens.settings.SettingsScreen
import com.example.simplechat.ui.screens.splash.SplashScreen

@Composable
fun MainScreen() {
    val appState = rememberAppState()
    NavHost(
        navController = appState.navController,
        startDestination = Screens.Splash.route
    ) {
        composable(Screens.Splash.route) {
            SplashScreen( openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) } )
        }
        composable(Screens.Chat.route) {
            ChatScreen( onNavigateToSettings =  { appState.navigate(Screens.Settings.route) })
        }
        composable(Screens.Settings.route) {
            SettingsScreen(onNavigateToChat =  { appState.navigate(Screens.Chat.route) })
        }
    }
}

@Stable
class SimpleChatState(
    val navController: NavHostController,
) {
    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }
    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    SimpleChatState(navController)
}

sealed class Screens(val route: String) {
    data object Splash : Screens("splash")
    data object Chat : Screens("chat")
    data object Settings : Screens("settings")
}