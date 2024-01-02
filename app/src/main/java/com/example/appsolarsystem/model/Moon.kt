package com.example.appsolarsystem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/**
 * Serializable data class representing information about a moon in the solar system.
 *
 * @param moonID The unique identifier for the moon.
 * @param planetID The identifier of the planet to which the moon belongs.
 * @param name The name of the moon.
 * @param imageUrl The URL of an image representing the moon.
 * @param description A brief description of the moon.
 * @param diameter The diameter of the moon.
 * @param mass The mass of the moon.
 */

@Serializable
data class Moon(
    @SerialName("MoonID") val moonID: Int,
    @SerialName("PlanetID") val planetID: Int,
    @SerialName("Name") val name: String,
    @SerialName("ImageUrl") val imageUrl: String,
    @SerialName("Description") val description: String,
    @SerialName("Diameter") val diameter: Double,
    @SerialName("Mass") val mass: Double
)
