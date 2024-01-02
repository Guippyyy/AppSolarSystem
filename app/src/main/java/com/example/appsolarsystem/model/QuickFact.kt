package com.example.appsolarsystem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/**
 * Serializable data class representing quick facts about a planet in the solar system.
 *
 * @property id The unique identifier for the quick fact.
 * @property planetID The identifier of the planet to which the quick fact belongs.
 * @property recordedBy The entity or person who recorded the quick fact.
 * @property firstRecorded The date or time when the quick fact was first recorded.
 * @property surfaceTempurature The surface temperature of the planet (as a String).
 * @property orbitPeriod The orbital period of the planet (as a String).
 * @property orbitDistance The orbital distance of the planet (as a String).
 * @property knownRings The number of known rings around the planet.
 * @property notableMoons A description of notable moons associated with the planet.
 * @property knownMoons The number of known moons around the planet.
 * @property equatorialCircumference The equatorial circumference of the planet (as a String).
 * @property polarDiameter The polar diameter of the planet (as a String).
 * @property equatorialDiameter The equatorial diameter of the planet (as a String).
 * @property mass The mass of the planet (as a String).
 */
@Serializable
data class QuickFact(
    @SerialName("Id") val id: Int,
    @SerialName("PlanetID") val planetID: Int,
    @SerialName("RecordedBy") val recordedBy: String?,
    @SerialName("FirstRecord") val firstRecorded: String?,
    @SerialName("SurfaceTempurature") val surfaceTempurature: String?,
    @SerialName("OrbitPeriod") val orbitPeriod: String?,
    @SerialName("OrbitDistance") val orbitDistance: String?,
    @SerialName("KnownRings") val knownRings: Int?,
    @SerialName("NotableMoons") val notableMoons: String?,
    @SerialName("KnownMoons") val knownMoons: Int?,
    @SerialName("EquatorialCircumference") val equatorialCircumference: String?,
    @SerialName("PolarDiameter") val polarDiameter: String?,
    @SerialName("EquatorialDiameter") val equatorialDiameter: String?,
    @SerialName("Mass") val mass: String?
)
