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
import com.example.appsolarsystem.data.planets.planetInfo.PlanetInfoRepository
import com.example.appsolarsystem.data.quickFacts.QuickFactRepository
import com.example.appsolarsystem.model.PlanetInfo
import com.example.appsolarsystem.model.QuickFact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface QuickFactUiState {
    data class Success(val quickFact : List<QuickFact>) : QuickFactUiState
    data class Error(val errorMessage: String) : QuickFactUiState
    object Loading : QuickFactUiState
}


class QuickFactViewModel(private val quickFactRepository: QuickFactRepository) : ViewModel() {
    private var quickFactUiState: QuickFactUiState by mutableStateOf(QuickFactUiState.Loading)

    private val _quickFacts = MutableStateFlow<List<QuickFact>>(emptyList())
    val quickFacts: StateFlow<List<QuickFact>> get() = _quickFacts

    init {
        getQuickFacts()
    }

    private fun getQuickFacts() {
        viewModelScope.launch {
            quickFactUiState = QuickFactUiState.Loading
            quickFactUiState = try {
                val quickFactsFlow = GlobalPlanet.planet?.let {
                    quickFactRepository.getAllQuickFactByPlanetStream(
                        it.planetID)
                }

                val quickFacts: MutableList<QuickFact> = mutableListOf()
                if (quickFactsFlow != null) {
                    quickFactsFlow.collect { pa ->
                        _quickFacts.value = pa as List<QuickFact>
                    }
                }

                QuickFactUiState.Success(quickFacts)
            } catch (e: IOException) {
                Log.d("PlanetInfoViewModel", "IOException")
                Log.d("PlanetInfoViewModel", e.message.toString())
                Log.d("PlanetInfoViewModel", e.stackTraceToString())
                QuickFactUiState.Error("IOException error: ${e.message}")
            } catch (e: HttpException) {
                Log.d("PlanetInfoViewModel", "HttpException")
                Log.d("PlanetInfoViewModel", e.message.toString())
                Log.d("PlanetInfoViewModel", e.stackTraceToString())
                QuickFactUiState.Error("HttpException error: ${e.message}")
            } catch (e: Exception) {
                Log.d("PlanetInfoViewModel", "Exception")
                Log.d("PlanetInfoViewModel", e.message.toString())
                Log.d("PlanetInfoViewModel", e.stackTraceToString())
                QuickFactUiState.Error("Exception error: ${e.message}")
            }
        }
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val quickFactRepository = application.container.quickFactRepository
                QuickFactViewModel(quickFactRepository = quickFactRepository)
            }
        }
    }
}
