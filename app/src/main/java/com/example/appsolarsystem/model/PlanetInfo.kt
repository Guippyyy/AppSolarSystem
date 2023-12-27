package com.example.appsolarsystem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PlanetInfo(
    @SerialName("id") val id: Int,
    @SerialName("PlanetID") val PlanetID : Int,
    @SerialName("intro") val intro : String,
    @SerialName("textplanet") val textplanet : String,
    @SerialName("formation") val formation : String,
    @SerialName("distance") val distance : String,
    @SerialName("orbit") val orbit : String
)