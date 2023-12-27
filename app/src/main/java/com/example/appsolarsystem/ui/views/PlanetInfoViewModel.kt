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
import com.example.appsolarsystem.data.planets.planetInfo.PlanetInfoRepository
import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.PlanetInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PlanetInfoUiState {
    data class Success(val PlanetInfos: List<PlanetInfo>) : PlanetInfoUiState
    data class Error(val errorMessage: String) : PlanetInfoUiState
    object Loading : PlanetInfoUiState
}


class PlanetInfoViewModel(private val planetInfoRepository: PlanetInfoRepository) : ViewModel() {
    private var planetInfoUiState: PlanetInfoUiState by mutableStateOf(PlanetInfoUiState.Loading)

    private val _planetInfos = MutableStateFlow<List<PlanetInfo>>(emptyList())
    val planetInfos: StateFlow<List<PlanetInfo>> get() = _planetInfos

    init {
        getPlanetInfos()
    }

    private fun getPlanetInfos() {
        viewModelScope.launch {
            planetInfoUiState = PlanetInfoUiState.Loading
            planetInfoUiState = try {
                val planetInfosFlow = GlobalPlanet.planet?.let {
                    planetInfoRepository.getAllPlanetInfoByPlanetStream(
                        it.planetID)
                }

                val planetInfos: MutableList<PlanetInfo> = mutableListOf()
                if (planetInfosFlow != null) {
                    planetInfosFlow.collect { pa ->
                        _planetInfos.value = pa as List<PlanetInfo>
                    }
                }

                PlanetInfoUiState.Success(planetInfos)
            } catch (e: IOException) {
                Log.d("PlanetInfoViewModel", "IOException")
                Log.d("PlanetInfoViewModel", e.message.toString())
                Log.d("PlanetInfoViewModel", e.stackTraceToString())
                PlanetInfoUiState.Error("IOException error: ${e.message}")
            } catch (e: HttpException) {
                Log.d("PlanetInfoViewModel", "HttpException")
                Log.d("PlanetInfoViewModel", e.message.toString())
                Log.d("PlanetInfoViewModel", e.stackTraceToString())
                PlanetInfoUiState.Error("HttpException error: ${e.message}")
            } catch (e: Exception) {
                Log.d("PlanetInfoViewModel", "Exception")
                Log.d("PlanetInfoViewModel", e.message.toString())
                Log.d("PlanetInfoViewModel", e.stackTraceToString())
                PlanetInfoUiState.Error("Exception error: ${e.message}")
            }
        }
    }

//    fun getMoonsByPlanetId(id: Int) {
//
//        viewModelScope.launch {
//            try{
//
//                moonRepository.getAllMoonsByPlanetStream(id)
//
//            } catch ( e : Exception){
//                Log.d("TimeSlotViewModel", "Exception")
//                Log.d("TimeSlotViewModel", e.message.toString())
//                Log.d("TimeSlotViewModel", e.stackTraceToString())
//            }
//
//        }
//
//
//    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val planetInfoRepository = application.container.planetInfoRepository
                PlanetInfoViewModel(planetInfoRepository = planetInfoRepository)
            }
        }
    }
}
