package com.example.appsolarsystem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuickFact (
    @SerialName("Id") val id : Int,
    @SerialName("PlanetID") val planetID : Int,
    @SerialName("RecordedBy") val recordedBy : String?,
    @SerialName("FirstRecord") val firstRecorded : String?,
    @SerialName("SurfaceTempurature")  val surfaceTempurature : String?,
    @SerialName("OrbitPeriod") val orbitPeriod : String?,
    @SerialName("OrbitDistance") val orbitDistance : String?,
    @SerialName("KnownRings") val knownRings : Int?,
    @SerialName("NotableMoons") val notableMoons : String?,
    @SerialName("KnownMoons") val knownMoons : Int?,
    @SerialName("EquatorialCircumference") val equatorialCircumference : String?,
    @SerialName("PolarDiameter") val polarDiameter : String?,
    @SerialName("EquatorialDiameter") val equatorialDiameter : String?,
    @SerialName("Mass") val mass : String?
)