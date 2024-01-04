package com.example.appsolarsystem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing information about a planet in the solar system.
 *
 * @property id The unique identifier for the planet information.
 * @property PlanetID The identifier of the planet to which the information belongs.
 * @property intro Introduction or summary of the planet information.
 * @property textplanet Detailed text about the planet.
 * @property formation Information about the planet's formation.
 * @property distance Information about the planet's distance.
 * @property orbit Information about the planet's orbit.
 */
@Serializable
data class PlanetInfo(
    @SerialName("id") val id: Int,
    @SerialName("PlanetID") val PlanetID: Int,
    @SerialName("intro") val intro: String?,
    @SerialName("textplanet") val textplanet: String?,
    @SerialName("formation") val formation: String?,
    @SerialName("distance") val distance: String?,
    @SerialName("orbit") val orbit: String?
)
