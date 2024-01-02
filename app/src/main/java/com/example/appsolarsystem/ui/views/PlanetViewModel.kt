package com.example.appsolarsystem.ui.views

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.network.HttpException
import com.example.appsolarsystem.MyApplication
import com.example.appsolarsystem.data.GlobalPlanet
import com.example.appsolarsystem.data.planets.PlanetRepository
import com.example.appsolarsystem.model.Planet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * Represents the possible UI states for Planet information.
 */
sealed interface PlanetUiState {

    /**
     * Represents a successful state with a list of planets.
     *
     * @property planets The list of planets.
     */
    data class Success(val planets: List<Planet>) : PlanetUiState

    /**
     * Represents an error state with an error message.
     *
     * @property errorMessage The error message.
     */
    data class Error(val errorMessage: String) : PlanetUiState

    /**
     * Represents the loading state.
     */
    object Loading : PlanetUiState
}

/**
 * ViewModel for managing Planet data in the application.
 *
 * @property planetRepository The repository for fetching Planet data.
 */
class PlanetViewModel(private val planetRepository: PlanetRepository) : ViewModel() {

    /**
     * Mutable state for holding the current Planet UI state.
     */
    var planetUiState: PlanetUiState by mutableStateOf(PlanetUiState.Loading)
        private set

    /**
     * StateFlow for providing a reactive stream of Planet data.
     */
    private val _planets = MutableStateFlow<List<Planet>>(emptyList())
    val planets: StateFlow<List<Planet>> get() = _planets

    /**
     * Initializes the PlanetViewModel by fetching the list of planets.
     */
    init {
        getPlanets()
    }

    /**
     * Fetches the list of planets from the repository and updates the UI state accordingly.
     */
    private fun getPlanets() {
        viewModelScope.launch {
            planetUiState = PlanetUiState.Loading
            planetUiState = try {
                val planetsFlow = planetRepository.getAllPlanetsStream()
                planetsFlow.collect { pa ->
                    _planets.value = pa
                }
                PlanetUiState.Success(planets = planets.value)
            } catch (e: IOException) {
                Log.d("PatientViewModel", "IOException")
                Log.d("PatientViewModel", e.message.toString())
                Log.d("PatientViewModel", e.stackTraceToString())
                PlanetUiState.Error("IOException error: ${e.message}")
            } catch (e: HttpException) {
                Log.d("PatientViewModel", "HttpException")
                Log.d("PatientViewModel", e.message.toString())
                Log.d("PatientViewModel", e.stackTraceToString())
                PlanetUiState.Error("HttpException error: ${e.message}")
            } catch (e: Exception) {
                Log.d("PatientViewModel", "Exception")
                Log.d("PatientViewModel", e.message.toString())
                Log.d("PatientViewModel", e.stackTraceToString())
                PlanetUiState.Error("Exception error: ${e.message}")
            }
        }
    }

    /**
     * Selects a specific planet to be used globally in the application.
     *
     * @param planet The selected planet.
     */
    fun selectPlanet(planet: Planet) {
        GlobalPlanet.planet = planet
    }

    /**
     * Companion object for providing a ViewModelProvider.Factory for PlanetViewModel.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val planetRepository = application.container.planetRepository
                PlanetViewModel(planetRepository = planetRepository)
            }
        }
    }

}
