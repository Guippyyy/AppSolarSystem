package com.example.appsolarsystem.ui.views


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.appsolarsystem.data.GlobalPlanet
import com.example.appsolarsystem.data.planets.PlanetRepository
import com.example.appsolarsystem.model.Planet
import io.mockk.MockK
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class PlanetViewModelTest {


        private val testDispatcher = TestCoroutineDispatcher()

        // Replace with your repository mock
        private val planetRepository = mockk<PlanetRepository>()

        private lateinit var viewModel: PlanetViewModel

        @Before
        fun setup() {
            Dispatchers.setMain(testDispatcher)
            viewModel = PlanetViewModel(planetRepository)
        }

        @After
        fun tearDown() {
            Dispatchers.resetMain()
            testDispatcher.cleanupTestCoroutines()
        }

        @Test
        fun `getPlanets should update UI state and planets flow on success`() {
            // Arrange
            val planetsList = listOf(Planet(1,"Earth", "", "", 3 ,3.4, 4.0), Planet(2,"Mars", "", "", 3 ,3.4, 4.0))
            coEvery { planetRepository.getAllPlanetsStream() } returns flowOf(planetsList)

            // Act
            viewModel.getPlanets()

            // Assert
            assert(viewModel.planetUiState is PlanetUiState.Success)
            assert(viewModel.planets.value == planetsList)
        }

        @Test
        fun `getPlanets should handle IOException and update UI state`() {
            // Arrange
            coEvery { planetRepository.getAllPlanetsStream() } throws IOException("Test IOException")

            // Act
            viewModel.getPlanets()

            // Assert
            assert(viewModel.planetUiState is PlanetUiState.Error)
            // Additional assertions based on your specific error handling
        }

        @Test
        fun `selectPlanet should set GlobalPlanet's planet`() {
            // Arrange
            val selectedPlanet = Planet(1,"Earth", "", "", 3 ,3.4, 4.0)

            // Act
            viewModel.selectPlanet(selectedPlanet)

            // Assert
            assert(GlobalPlanet.planet == selectedPlanet)
        }

        // Additional tests for error handling, corner cases, etc.
    }

