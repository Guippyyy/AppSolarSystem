package com.example.appsolarsystem.navigation

import android.net.Uri
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
import com.example.appsolarsystem.ui.views.PlanetViewModel


@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val planetViewModel: PlanetViewModel = viewModel()

    Scaffold(
        // Other scaffold parameters...
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screens.SolarSystemScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.SolarSystemScreen.name) {
                SolarSystemScreen(
                    onNextButtonClicked = { planet ->
                        navController.navigate(
                            "${Screens.PlanetIdScreen.name}/${Uri.encode(planet.planetId.toString())}/${Uri.encode(planet.name)}/${
                                Uri.encode(
                                    planet.imageUrl
                                )
                            }/${Uri.encode(planet.diameter.toString())}/${Uri.encode(planet.mass.toString())}/${Uri.encode(planet.gravity.toString())}"
                        )
                    }
                )
            }

            composable(route = "${Screens.PlanetIdScreen.name}/{planetId}/{planetName}/{planetImage}/{planetDiameter}/{planetMass}/{planetGravity}") { backStackEntry ->
                val planetName = backStackEntry.arguments?.getString("planetName") ?: ""
                val planetImage = backStackEntry.arguments?.getString("planetImage") ?: ""
                val planetId = backStackEntry.arguments?.getString("planetId") ?: ""
                val planetDiameter = backStackEntry.arguments?.getString("planetDiameter") ?: ""
                val planetMass = backStackEntry.arguments?.getString("planetMass") ?: ""
                val planetGravity = backStackEntry.arguments?.getString("planetGravity") ?: ""
                PlanetIdScreen(planetId,planetName, planetImage, planetDiameter, planetMass, planetGravity) {

                }
            }
        }
    }
}
