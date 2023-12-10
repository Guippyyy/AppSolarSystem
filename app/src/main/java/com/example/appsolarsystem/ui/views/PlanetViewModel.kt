package com.example.appsolarsystem.ui.views

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.network.PlanetApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface PlanetUiState {

    data class Success(val planets: List<Planet>) : PlanetUiState
    data class Error(val errorMessage: String) : PlanetUiState
    object Loading : PlanetUiState

}

class PlanetViewModel() : ViewModel(){

    private var planetUiState: PlanetUiState by mutableStateOf(PlanetUiState.Loading)
    private val _planets = MutableStateFlow<List<Planet>>(emptyList())
    val planets: StateFlow<List<Planet>> get() = _planets

    private fun setPlanets(newPlanets : List<Planet>){
        _planets.value = newPlanets
    }

    init{
        getPlanets()
    }

    private fun getPlanets(){
        viewModelScope.launch {
            planetUiState = PlanetUiState.Loading
            planetUiState = try{
                val listResult = PlanetApi.retrofitService.getPlanets()
                setPlanets(listResult)
                PlanetUiState.Success(listResult)
            }catch (e: IOException) {
                Log.d("here", "${e.message}")
                PlanetUiState.Error("Network error: ${e.message}")
            } catch (e: HttpException) {
                PlanetUiState.Error("HTTP error: ${e.message}")
            }

        }
    }
}