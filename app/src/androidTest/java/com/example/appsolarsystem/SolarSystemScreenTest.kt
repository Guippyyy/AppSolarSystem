package com.example.appsolarsystem

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performGesture
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipe
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.test.swipeUp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.appsolarsystem.data.planets.FakePlanetRepository
import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.ui.screens.SolarSystemScreen
import com.example.appsolarsystem.ui.views.PlanetViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.math.max


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

        Thread.sleep(2000)

       composeTestRule.onNodeWithText("Explore Our Solar System").assertExists()
       composeTestRule.onNodeWithText("Embark on a journey through the cosmos and discover the wonders of our solar system. Unveil the mysteries of distant planets, their unique landscapes, and the fascinating stories they hold.").assertExists()

       composeTestRule.onNodeWithText("Explore Our Solar System").performTouchInput { swipeUp()}

       composeTestRule.waitForIdle()
       Thread.sleep(2000)
       composeTestRule.onNodeWithText("planet1").assertExists()

       composeTestRule.onNodeWithText("planet1").performTouchInput { swipeUp()}

       composeTestRule.waitForIdle()
       Thread.sleep(2000)
       composeTestRule.onNodeWithText("planet2").assertExists()

       composeTestRule.onNodeWithText("planet2").performTouchInput { swipeUp()}

       composeTestRule.waitForIdle()
       Thread.sleep(2000)
       composeTestRule.onNodeWithText("planet3").assertDoesNotExist()
       composeTestRule.onNodeWithText("test").assertExists()
   }
}