package com.example.appsolarsystem

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appsolarsystem.data.planets.FakePlanetRepository
import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.ui.screens.SolarSystemScreen
import com.example.appsolarsystem.ui.views.PlanetViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SolarSystemScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()



    @Before
    fun setup(){
        val planet1 = Planet(1, "planet1","fdsfde", "test", 3, 3.0, 33.0)
        val planet2 = Planet(2, "planet2","fdsfde", "fdf", 3, 3.0, 33.0)

        val planetList = mutableListOf(planet1, planet2)
        val planetViewModel = PlanetViewModel(FakePlanetRepository(planetList))
        composeTestRule.setContent {
            SolarSystemScreen(
                planetViewModel = planetViewModel,
                onNextButtonClicked = {}
            )
        }
    }


   @Test
   fun solarScreen_verify(){

       composeTestRule.waitForIdle()

        Thread.sleep(5000)

       composeTestRule.onNodeWithText("Explore Our Solar System").assertExists()
       composeTestRule.onNodeWithText("Embark on a journey through the cosmos and discover the wonders of our solar system. Unveil the mysteries of distant planets, their unique landscapes, and the fascinating stories they hold.").assertExists()
       composeTestRule.onNodeWithText("planet1").assertExists()
       composeTestRule.onNodeWithText("planet2").assertExists()
       composeTestRule.onNodeWithText("planet3").assertDoesNotExist()
       composeTestRule.onNodeWithText("test").assertExists()
   }

//    @Test
//    fun solarScreen_navigate_planetIdScreen_verify(){
//
//        composeTestRule.waitForIdle()
//
//        Thread.sleep(3000)
//
//        composeTestRule.onNodeWithText("planet1").assertExists()
//        composeTestRule.onNodeWithText("planet1").performClick()
//
//
//
//
//
//    }

}