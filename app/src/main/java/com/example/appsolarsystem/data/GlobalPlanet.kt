package com.example.appsolarsystem.data

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appsolarsystem.model.Planet
import com.example.appsolarsystem.ui.views.MoonViewModel

/**
 * Singleton object to hold the currently selected [Planet].
 *
 * This object allows storing the selected planet globally within the application,
 * making it accessible from different parts of the code.
 */
object GlobalPlanet {
    /**
     * The currently selected [Planet]. This property can be null if no planet is selected.
     */
    var planet: Planet? = null
}
