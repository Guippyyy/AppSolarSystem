package com.example.appsolarsystem.navigation
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.appsolarsystem.R
@RunWith(AndroidJUnit4::class)
class AppNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun testOpenNavigationDrawerWithSwipe() {

        composeTestRule.setContent {
            AppNavigation()
        }


        composeTestRule.onRoot().performTouchInput {
            swipeRight()
        }


        composeTestRule.onNodeWithText("Home").assertExists()
        composeTestRule.onNodeWithText("Asteroids").assertExists()
        composeTestRule.onNodeWithText("Comets").assertExists()
        composeTestRule.onNodeWithText("The Asteroid Belt").assertExists()


    }
    @Test
    fun testNavigationDrawer() {
        // Launch the composable
        composeTestRule.setContent {
            AppNavigation()
        }

        composeTestRule.onRoot().performTouchInput {
            swipeRight()
        }

        composeTestRule.onNodeWithText("Home").assertExists()
        composeTestRule.onNodeWithText("Asteroids").assertExists()
        composeTestRule.onNodeWithText("Comets").assertExists()
        composeTestRule.onNodeWithText("The Asteroid Belt").assertExists()

        composeTestRule.onNodeWithText("Home").performClick()


    }

    @Test
    fun testNavigationBetweenScreens() {

        composeTestRule.setContent {
            AppNavigation()
        }

        composeTestRule.onRoot().performTouchInput {
            swipeRight()
        }
        composeTestRule.onNodeWithText("Asteroids").performClick()
        composeTestRule.onNodeWithTag("Asteroids").assertExists()
    }

    @Test
    fun testNavigationBetweenScreensAsteroidBelt() {

        composeTestRule.setContent {
            AppNavigation()
        }

        composeTestRule.onRoot().performTouchInput {
            swipeRight()
        }
        composeTestRule.onNodeWithText("The Asteroid Belt").performClick()
        composeTestRule.onNodeWithTag("The Asteroid Belt").assertExists()
    }
}
