package com.example.appsolarsystem

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.appsolarsystem.data.moons.FakeMoonRepository
import com.example.appsolarsystem.data.planets.FakePlanetRepository
import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.ui.screens.PlanetIdScreen
import com.example.appsolarsystem.ui.views.MoonViewModel
import com.example.appsolarsystem.ui.views.PlanetViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlanetIdScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup(){
        val planet1 = Planet(1, "planet1","fdsfde", "test", 3, 3.0, 33.0)
        val planetList = mutableListOf(planet1)
        val planetViewModel = PlanetViewModel(FakePlanetRepository(planetList))
        planetViewModel.selectPlanet(planet1)

        val moon1 = Moon(1,1,"moon", "fdf","test2",4.0,3.2 )
        val moonList = mutableListOf(moon1)
        val moonViewModel = MoonViewModel(FakeMoonRepository(moonList))

        composeTestRule.setContent {

            PlanetIdScreen(
                moonViewModel = moonViewModel
            )
        }
    }

    @Test
    fun plantIdScreen_Moon_verify(){
        Thread.sleep(4000)
        composeTestRule.onNodeWithText("planet1").assertExists()
        composeTestRule.onNodeWithText("moon").assertExists()
    }





}