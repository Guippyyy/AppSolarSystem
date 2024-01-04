package com.example.appsolarsystem.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.appsolarsystem.R
import com.example.appsolarsystem.navigation.AppNavigation
import com.example.appsolarsystem.navigation.Screens

/**
 * Composable function representing the main Solar System app, which includes a Modal Navigation Drawer
 * for navigation and the main content represented by [AppNavigation].
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun SolarSystemApp(){
    val navController = rememberNavController()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text(text = stringResource(R.string.drawerTitle), modifier = Modifier.padding(16.dp))
                GlideImage(model = R.drawable.rocket_up, contentDescription = "rocket_up")
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawerAsteroids)) },
                    selected = false,
                    onClick = { navController.navigate(Screens.AsteroidsScreen.name) }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawerComets)) },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawerConstellations)) },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawerTheAsteroidBelt)) },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawerGalaxies)) },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.drawerStars)) },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
            }
        }
    )
    {

    }
}