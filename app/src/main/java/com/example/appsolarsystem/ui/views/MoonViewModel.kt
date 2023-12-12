package com.example.appsolarsystem.ui.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appsolarsystem.model.Moon
import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.network.MoonApi
import com.example.appsolarsystem.network.PlanetApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MoonUiState {

    data class Success(val moons: List<Moon>) : MoonUiState
    object Error : MoonUiState
    object Loading : MoonUiState

}

class MoonViewModel : ViewModel(){

    var moonUiState: MoonUiState by mutableStateOf(MoonUiState.Loading)
    private val _moons = MutableStateFlow<List<Moon>>(emptyList())
    val moons: StateFlow<List<Moon>> get() = _moons

    private fun setMoons(newMoons : List<Moon>){
        _moons.value = newMoons
    }


    private fun getMoons(){
        viewModelScope.launch {
            moonUiState = MoonUiState.Loading
            moonUiState = try{
                val listResult = MoonApi.retrofitService.getMoons()
                setMoons(listResult)
                MoonUiState.Success(listResult)
            } catch (e: IOException) {
                MoonUiState.Error
            }
        }
    }

    fun getMoonsByPlanetId(planetId: Int){
        viewModelScope.launch {
            moonUiState = MoonUiState.Loading
            moonUiState = try {
                val listResult = MoonApi.retrofitService.getMoonsByPlanetId(planetId)
                setMoons(listResult)
                MoonUiState.Success(listResult)
            } catch (e: IOException) {
                MoonUiState.Error
            }
        }
    }
}