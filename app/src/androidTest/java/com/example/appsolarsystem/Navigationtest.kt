package com.example.appsolarsystem

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.appsolarsystem.data.planets.FakePlanetRepository
import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.navigation.Screens
import com.example.appsolarsystem.ui.screens.SolarSystemScreen
import com.example.appsolarsystem.ui.views.PlanetViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test



//class NavigationTest {
//
//
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
//
//    private lateinit var navController : NavHostController
//
//    @Test
//    fun setupSolarSystemScreenNavHost(){
//        navController = NavHostController(ApplicationProvider.getApplicationContext())
//        val planet1 = Planet(1, "planet1","fdsfde", "test", 3, 3.0, 33.0)
//        val planet2 = Planet(2, "planet2","fdsfde", "fdf", 3, 3.0, 33.0)
//
//        val planetList = mutableListOf(planet1, planet2)
//        val planetViewModel = PlanetViewModel(FakePlanetRepository(planetList))
//        composeTestRule.setContent {
//
//            SolarSystemScreen(
//                planetViewModel = planetViewModel ,
//                onNextButtonClicked = { planet ->
//                    navController.navigate(Screens.PlanetIdScreen.name)
//                })
//
//        }
//
//        composeTestRule.waitForIdle()
//
//        Thread.sleep(5000)
//        composeTestRule.onNodeWithText("planet1").assertExists()
//        composeTestRule.onNodeWithText("planet1").performClick()
//        assert(navController.currentBackStackEntry?.destination?.route == Screens.PlanetIdScreen.name)
//    }
//
//
//}