package com.example.appsolarsystem.navigation

import android.net.Uri
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appsolarsystem.ui.screens.PlanetIdScreen
import com.example.appsolarsystem.ui.screens.SolarSystemScreen
import com.example.appsolarsystem.ui.views.MoonViewModel
import com.example.appsolarsystem.ui.views.PlanetViewModel

/**
 * Composable function representing the main navigation flow of the Solar System app.
 */
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val planetViewModel: PlanetViewModel = viewModel(factory = PlanetViewModel.Factory)
    val moonViewModel : MoonViewModel = viewModel(factory = MoonViewModel.Factory)

    Scaffold(
        // Other scaffold parameters...
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screens.SolarSystemScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(
                route = Screens.SolarSystemScreen.name,
                enterTransition = {
                    fadeIn(animationSpec = tween(3000))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(1000))
                }
                ) {
                SolarSystemScreen(
                    planetViewModel = planetViewModel,
                    onNextButtonClicked = { planet ->
                        navController.navigate(
                            Screens.PlanetIdScreen.name
                        )
                    }
                )
            }

            composable(
                route = Screens.PlanetIdScreen.name,
                enterTransition = {
                    fadeIn(animationSpec = tween(3000))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(2000))
                }

                ) {
                PlanetIdScreen(moonViewModel = moonViewModel)
            }
        }
    }
}
