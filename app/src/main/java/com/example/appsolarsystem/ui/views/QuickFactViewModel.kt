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

/**
 * Represents the possible UI states for QuickFact information.
 */
sealed interface QuickFactUiState {

    /**
     * Represents a successful state with a list of QuickFact objects.
     *
     * @property quickFact The list of QuickFact objects.
     */
    data class Success(val quickFact: List<QuickFact>) : QuickFactUiState

    /**
     * Represents an error state with an error message.
     *
     * @property errorMessage The error message.
     */
    data class Error(val errorMessage: String) : QuickFactUiState

    /**
     * Represents the loading state.
     */
    object Loading : QuickFactUiState
}

/**
 * ViewModel for managing QuickFact data in the application.
 *
 * @property quickFactRepository The repository for fetching QuickFact data.
 */
class QuickFactViewModel(private val quickFactRepository: QuickFactRepository) : ViewModel() {

    /**
     * Mutable state for holding the current QuickFact UI state.
     */
    private var quickFactUiState: QuickFactUiState by mutableStateOf(QuickFactUiState.Loading)

    /**
     * StateFlow for providing a reactive stream of QuickFact data.
     */
    private val _quickFacts = MutableStateFlow<List<QuickFact>>(emptyList())
    val quickFacts: StateFlow<List<QuickFact>> get() = _quickFacts

    /**
     * Initializes the QuickFactViewModel by fetching the list of QuickFacts.
     */
    init {
        getQuickFacts()
    }

    /**
     * Fetches the list of QuickFacts from the repository and updates the UI state accordingly.
     */
    private fun getQuickFacts() {
        viewModelScope.launch {
            quickFactUiState = QuickFactUiState.Loading
            quickFactUiState = try {
                val quickFactsFlow = GlobalPlanet.planet?.let {
                    quickFactRepository.getAllQuickFactByPlanetStream(
                        it.planetID
                    )
                }

                val quickFacts: MutableList<QuickFact> = mutableListOf()
                if (quickFactsFlow != null) {
                    quickFactsFlow.collect { pa ->
                        _quickFacts.value = pa as List<QuickFact>
                    }
                }

                QuickFactUiState.Success(quickFacts)
           } catch (e: IOException) {
                Log.d("QuickFactsViewModel", "IOException")
                Log.d("QuickFactsViewModel", e.message.toString())
                Log.d("QuickFactsViewModel", e.stackTraceToString())
                QuickFactUiState.Error("IOException error: ${e.message}")
            } catch (e: HttpException) {
                Log.d("QuickFactsViewModel", "HttpException")
                Log.d("QuickFactsViewModel", e.message.toString())
                Log.d("QuickFactsViewModel", e.stackTraceToString())
                QuickFactUiState.Error("HttpException error: ${e.message}")
            } catch (e: Exception) {
                Log.d("QuickFactsViewModel", "Exception")
                Log.d("QuickFactsViewModel", e.message.toString())
                Log.d("QuickFactsViewModel", e.stackTraceToString())
                QuickFactUiState.Error("Exception error: ${e.message}")
            }
        }
    }

    /**
     * Companion object for providing a ViewModelProvider.Factory for QuickFactViewModel.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val quickFactRepository = application.container.quickFactRepository
                QuickFactViewModel(quickFactRepository = quickFactRepository)
            }
        }
    }
}
