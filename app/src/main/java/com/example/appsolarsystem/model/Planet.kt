package com.example.appsolarsystem.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Planet(
    @SerialName("PlanetID") val planetId: Int,
    @SerialName("Name") val name: String,
    @SerialName("ImageUrl") val imageUrl: String,
    @SerialName("Description") val description: String
)
