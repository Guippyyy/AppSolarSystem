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

sealed interface PlanetUiState {
    data class Success(val planets: List<Planet>) : PlanetUiState
    data class Error(val errorMessage: String) : PlanetUiState
    object Loading : PlanetUiState
}


class PlanetViewModel(private val planetRepository: PlanetRepository) : ViewModel() {
    var planetUiState: PlanetUiState by mutableStateOf(PlanetUiState.Loading)
    private set
    private val _planets = MutableStateFlow<List<Planet>>(emptyList())
    val planets: StateFlow<List<Planet>> get() = _planets

    init {
        getPlanets()
    }

    private fun getPlanets() {
        viewModelScope.launch {
            planetUiState = PlanetUiState.Loading
            planetUiState = try {
                val planetsFlow = planetRepository.getAllPlanetsStream()

                Log.d("here", "${planetsFlow}")
                planetsFlow.collect { pa ->

                    _planets.value = pa

                }

                Log.d("hello", "im here")

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

    fun selectPlanet(planet : Planet){
        GlobalPlanet.planet = planet
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val planetRepository = application.container.planetRepository
                PlanetViewModel(planetRepository = planetRepository)
            }
        }
    }
}