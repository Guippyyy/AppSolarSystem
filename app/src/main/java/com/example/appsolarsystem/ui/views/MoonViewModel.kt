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
import com.example.appsolarsystem.data.moons.MoonRepository
import com.example.appsolarsystem.model.Moon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * Represents the possible UI states for Moon information.
 */
sealed interface MoonUiState {

    /**
     * Represents a successful state with a list of moons.
     *
     * @property moons The list of moons.
     */
    data class Success(val moons: List<Moon>) : MoonUiState

    /**
     * Represents an error state with an error message.
     *
     * @property errorMessage The error message.
     */
    data class Error(val errorMessage: String) : MoonUiState

    /**
     * Represents the loading state.
     */
    object Loading : MoonUiState
}

/**
 * ViewModel for managing Moon data in the application.
 *
 * @property moonRepository The repository for fetching Moon data.
 */
class MoonViewModel(private val moonRepository: MoonRepository) : ViewModel() {

    /**
     * Mutable state for holding the current Moon UI state.
     */
    private var moonUiState: MoonUiState by mutableStateOf(MoonUiState.Loading)

    /**
     * StateFlow for providing a reactive stream of Moon data.
     */
    private val _moons = MutableStateFlow<List<Moon>>(emptyList())
    val moons: StateFlow<List<Moon>> get() = _moons

    /**
     * Initializes the MoonViewModel by fetching the list of moons.
     */
    init {
        getMoons()
    }

    /**
     * Fetches the list of moons from the repository and updates the UI state accordingly.
     */
    private fun getMoons() {
        viewModelScope.launch {
            moonUiState = MoonUiState.Loading
            moonUiState = try {
                val moonsFlow = GlobalPlanet.planet?.let {
                    moonRepository.getAllMoonsByPlanetStream(
                        it.planetID
                    )
                }

                val moons: MutableList<Moon> = mutableListOf()
                if (moonsFlow != null) {
                    moonsFlow.collect { pa ->
                        _moons.value = pa as List<Moon>
                    }
                }

                MoonUiState.Success(moons)
      } catch (e: IOException) {
                Log.d("PatientViewModel", "IOException")
                Log.d("PatientViewModel", e.message.toString())
                Log.d("PatientViewModel", e.stackTraceToString())
                MoonUiState.Error("IOException error: ${e.message}")
            } catch (e: HttpException) {
                Log.d("PatientViewModel", "HttpException")
                Log.d("PatientViewModel", e.message.toString())
                Log.d("PatientViewModel", e.stackTraceToString())
                MoonUiState.Error("HttpException error: ${e.message}")
            } catch (e: Exception) {
                Log.d("PatientViewModel", "Exception")
                Log.d("PatientViewModel", e.message.toString())
                Log.d("PatientViewModel", e.stackTraceToString())
                MoonUiState.Error("Exception error: ${e.message}")
            }
        }
    }

    /**
     * Fetches the list of moons for a specific planet ID.
     *
     * @param id The ID of the planet.
     */
    fun getMoonsByPlanetId(id: Int) {
        viewModelScope.launch {
            try {
                moonRepository.getAllMoonsByPlanetStream(id)
            } catch ( e : Exception){
                Log.d("TimeSlotViewModel", "Exception")
                Log.d("TimeSlotViewModel", e.message.toString())
                Log.d("TimeSlotViewModel", e.stackTraceToString())
            }
        }
    }

    /**
     * Companion object for providing a ViewModelProvider.Factory for MoonViewModel.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val moonRepository = application.container.moonRepository
                MoonViewModel(moonRepository = moonRepository)
            }
        }
    }
}
