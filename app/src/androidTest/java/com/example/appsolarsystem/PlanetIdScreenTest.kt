package com.example.appsolarsystem

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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
        val moon1 = Moon(10,10,"Moon", "fdf","test2",4.0,3.2 )
        val planet1 = Planet(10, "planet1","fdsfde", "test", 3, 3.0, 33.0)
        val moonList = mutableListOf(moon1)
        val planetList = mutableListOf(planet1)
        val planetViewModel = PlanetViewModel(FakePlanetRepository(planetList))
        val moonViewModel = MoonViewModel(FakeMoonRepository(moonList))


        planetViewModel.selectPlanet(planet1)
        composeTestRule.setContent {
            PlanetIdScreen()
        }
    }

    @Test
    fun plantIdScreen_Planet_verify(){
        Thread.sleep(4000)
        composeTestRule.onNodeWithText("planet1").assertExists()

    }


    @Test
    fun planetIdScreen_quickFactsButton_verify(){

        Thread.sleep(4000)

        composeTestRule.onNodeWithTag("facts", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Quick Facts", useUnmergedTree = true).performClick()

        composeTestRule.waitForIdle()
        Thread.sleep(5000)


    }




}