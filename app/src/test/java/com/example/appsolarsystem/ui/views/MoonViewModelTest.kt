package com.example.appsolarsystem.ui.views

import com.example.appsolarsystem.data.moons.MoonRepository
import com.example.appsolarsystem.data.planets.PlanetRepository
import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.Planet
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
class MoonViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()

    // Replace with your repository mock
    private val moonRepository = mockk<MoonRepository>()
    private val planetRepository = mockk<PlanetRepository>()
    private lateinit var viewModel: MoonViewModel
    private lateinit var viewModel2 : PlanetViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel2 = PlanetViewModel(planetRepository)
        viewModel = MoonViewModel(moonRepository)

        val planet = Planet(1, "planet", "", "", 4, 3.3, 3.3)
        viewModel2.selectPlanet(planet)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getMoons should update UI state and moons flow on success`() {
        // Arrange
        val moonsList = listOf(Moon(1,1, "Moon", "", "" , 6.9, 4.3 ), Moon(2,1, "Phobos", "", "" , 6.9, 4.3))

        coEvery { moonRepository.getAllMoonsByPlanetStream(any()) } returns flowOf(moonsList)

        // Act
        viewModel.getMoons()

        // Assert
        assert(viewModel.moonUiState is MoonUiState.Success)
        assert(viewModel.moons.value == moonsList)
    }

    @Test
    fun `getMoons should handle IOException and update UI state`() {
        // Arrange
        coEvery { moonRepository.getAllMoonsByPlanetStream(any()) } throws IOException("Test IOException")

        // Act
        viewModel.getMoons()

        // Assert
        assert(viewModel.moonUiState is MoonUiState.Error)

    }


}
