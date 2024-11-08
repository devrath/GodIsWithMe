package com.istudio.godiswithme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.istudio.godiswithme.architecture.features.home.HomeScreen
import com.istudio.godiswithme.architecture.features.home.settings.AppLanguage
import com.istudio.godiswithme.architecture.features.splash.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    languageSelected: (AppLanguage) -> Unit,
    invokeAudioService : () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(
                navController = navController,
                invokeAudioService = invokeAudioService,
                languageSelected = languageSelected
            )
        }
    }
}