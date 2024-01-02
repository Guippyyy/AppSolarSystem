package com.example.appsolarsystem.navigation
/**
 * Enum class representing different screens in the Solar System app.
 * Each screen corresponds to a specific destination in the navigation flow.
 */
enum class Screens {
    /**
     * Represents the main screen displaying solar system information.
     */
    SolarSystemScreen,

    /**
     * Represents the screen displaying detailed information about a specific planet.
     * This screen requires a planet ID as a parameter.
     */
    PlanetIdScreen // Gets a planet ID as a parameter
}
