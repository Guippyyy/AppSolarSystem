package com.example.appsolarsystem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Serializable data class representing information about a planet in the solar system.
 *
 * @property planetID The unique identifier for the planet.
 * @property name The name of the planet.
 * @property imageUrl The URL of an image representing the planet.
 * @property description A brief description of the planet.
 * @property diameter The diameter of the planet.
 * @property mass The mass of the planet.
 * @property gravity The gravity of the planet.
 */

@Serializable
data class Planet(
    @SerialName("PlanetID") val planetID: Int,
    @SerialName("Name") val name: String,
    @SerialName("ImageUrl") val imageUrl: String,
    @SerialName("Description") val description: String,
    @SerialName("Diameter") val diameter: Int,
    @SerialName("Mass") val mass: Double,
    @SerialName("Gravity") val gravity: Double
)
