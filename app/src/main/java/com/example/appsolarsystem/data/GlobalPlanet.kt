package com.example.appsolarsystem.data

import com.example.appsolarsystem.model.Planet
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
