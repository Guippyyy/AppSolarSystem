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
import com.example.appsolarsystem.data.moons.MoonRepository
import com.example.appsolarsystem.model.Moon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MoonUiState {
    data class Success(val moons: List<Moon>) : MoonUiState
    data class Error(val errorMessage: String) : MoonUiState
    object Loading : MoonUiState
}


class MoonViewModel(private val moonRepository: MoonRepository) : ViewModel() {
    private var moonUiState: MoonUiState by mutableStateOf(MoonUiState.Loading)

    private val _moons = MutableStateFlow<List<Moon>>(emptyList())
    val moons: StateFlow<List<Moon>> get() = _moons

    init {
        getPlanets()
    }

    private fun getPlanets() {
        viewModelScope.launch {
            moonUiState = MoonUiState.Loading
            moonUiState = try {
                val moonsFlow = moonRepository.getAllMoonsStream()

                val moons: MutableList<Moon> = mutableListOf()
                moonsFlow.collect { pa ->
                    moons.addAll(pa)
                }

                _moons.value = moons
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

    fun getMoonsByPlanetId(id: Int) {

        viewModelScope.launch {
            try{

                moonRepository.getMoonStream(id)

            } catch ( e : Exception){
                Log.d("TimeSlotViewModel", "Exception")
                Log.d("TimeSlotViewModel", e.message.toString())
                Log.d("TimeSlotViewModel", e.stackTraceToString())
            }

        }


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
