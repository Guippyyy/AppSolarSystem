package com.example.appsolarsystem.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.appsolarsystem.R
import com.example.appsolarsystem.ui.screens.AsteroidBeltScreen
import com.example.appsolarsystem.ui.screens.AsteroidsScreen
import com.example.appsolarsystem.ui.screens.PlanetIdScreen
import com.example.appsolarsystem.ui.screens.SolarSystemScreen
import com.example.appsolarsystem.ui.views.PlanetViewModel
import kotlinx.coroutines.launch

/**
 * Composable function representing the main navigation flow of the Solar System app.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val planetViewModel: PlanetViewModel = viewModel(factory = PlanetViewModel.Factory)
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                content = {
                    LazyColumn {

                        item{
                            Text(text = stringResource(R.string.drawerTitle), modifier = Modifier.padding(16.dp))
                            GlideImage(model = R.drawable.rocket_up, contentDescription = "rocket_up")
                            Divider()}

                        item{
                            NavigationDrawerItem(
                                label = { Text(text = "Home") },
                                selected = false,
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isOpen) close() else open()
                                        }
                                    }
                                    navController.navigate(Screens.SolarSystemScreen.name)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text(text = stringResource(R.string.drawerAsteroids)) },
                                selected = false,
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isOpen) close() else open()
                                        }
                                    }
                                    navController.navigate(Screens.AsteroidsScreen.name)
                                }
                            )
                            NavigationDrawerItem(
                                label = { Text(text = stringResource(R.string.drawerComets)) },
                                selected = false,
                                onClick = { /*TODO*/ }
                            )
                            NavigationDrawerItem(
                                label = { Text(text = stringResource(R.string.drawerTheAsteroidBelt)) },
                                selected = false,
                                onClick = {
                                    scope.launch {
                                        drawerState.apply {
                                            if (isOpen) close() else open()
                                        }
                                    }
                                    navController.navigate(Screens.AsteroidBeltScreen.name)
                                }
                            )
                        }

                    }
                }
            )
        }
    )
    {

        NavHost(
            navController = navController,
            startDestination = Screens.SolarSystemScreen.name,
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
                    onNextButtonClicked = {
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
                PlanetIdScreen()
            }

            composable(
                route = Screens.AsteroidsScreen.name,
                enterTransition = {
                    fadeIn(animationSpec = tween(3000))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(3000))
                }
            ){
                AsteroidsScreen()
            }
            composable(
                route = Screens.AsteroidBeltScreen.name,
                enterTransition = {
                    fadeIn(animationSpec = tween(3000))
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(3000))
                }
            ){
                AsteroidBeltScreen()
            }
        }
    }
}